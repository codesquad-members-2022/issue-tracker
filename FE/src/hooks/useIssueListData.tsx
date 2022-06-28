import { getIssueCount } from '@/api/issueList';
import { getIssueList } from '@/api/issueList';
import { IssueStateType } from '@/api/type';
import { useQuery } from 'react-query';
import { AxiosError } from 'axios';

function useIssueListData(state: IssueStateType = 'open') {
  return useQuery(['issueList', { status: 'open' }], () => getIssueList(state), {
    onSuccess: data => {
      console.log(data);
    },
    onError: (e: AxiosError) => {
      console.log(e.message);
    }
  });
}

function useIssueListCountData(state: IssueStateType = 'open') {
  return useQuery(['issueList', { status: 'open' }, 'count'], () => getIssueCount(state), {
    onSuccess: data => {
      console.log(state + ' _ ' + data);
    },
    onError: (e: AxiosError) => {
      console.log(e.message);
    }
  });
}

export { useIssueListData, useIssueListCountData };
