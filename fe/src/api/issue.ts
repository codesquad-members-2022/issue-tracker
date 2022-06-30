import axios, { AxiosError } from 'axios';
import { useQuery } from 'react-query';
import { IssueItemTypes } from 'components/Molecules/IssueList/IssueItem';
import { UserTypes, LabelTypes, MilestoneTypes } from 'components/types';
import { useNavigate } from 'react-router-dom';
import { jwtResponseTypes } from 'helpers/utils/fetchData';

export interface ServerDataTypes {
  openIssueCount: number;
  closedIssueCount: number;
  labelCount: number;
  milestoneCount: number;
  assignees: UserTypes[];
  labels: LabelTypes[];
  milestones: MilestoneTypes[];
  writers: UserTypes[];
  issues: {
    content: IssueItemTypes[];
  };
}

export const getReIssueToken = async () => {
  try {
    const { data } = await axios.get<jwtResponseTypes>(
      `${process.env.REACT_APP_SERVER_URL}/api/members/re-issue-token`,
    );
    axios.defaults.headers.common.Authorization = `Bearer ${data.accessToken}`;
  } catch (error) {
    const err = error as AxiosError;
    if (err.message === '벡엔드에서 주는 리프레시 토큰 만료 메세지') {
      window.localStorage.removeItem('refresh_token');

      const navigate = useNavigate();
      navigate('/');
    }
  }
};

export const getServerData = async (URL: string) => {
  try {
    const refreshToken = window.localStorage.getItem('refresh_token');

    axios.defaults.withCredentials = true;

    // Bearer 옆에는 액세스 토큰이 들어가야된다.
    axios.defaults.headers.common.Authorization = `Bearer ${refreshToken}`;
    const { data } = await axios.get(`${process.env.REACT_APP_SERVER_URL}/${URL}`);

    return data;
  } catch (error) {
    const err = error as AxiosError;
    console.log(err);

    if (err.message === '벡엔드에서 주는 엑세스 토큰 만료 메세지') {
      console.log(err.message);
      getReIssueToken();
    }

    return console.error('뭔가 잘못됌');
  }
};

const useGetIssueData = (page: number) => {
  return useQuery(`issueData-${page}`, () => getServerData(`api/issues?page=${page}`), {
    cacheTime: Infinity,
  });
};

export default useGetIssueData;
