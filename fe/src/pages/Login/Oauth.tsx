import { useQuery } from 'react-query';
import { Dispatch, SetStateAction } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

import axios from 'axios';

import { AuthTypes } from 'helpers/utils/fetchData';

const Oauth = ({ setIsOAuth }: { setIsOAuth: Dispatch<SetStateAction<boolean>> }) => {
  const location = useLocation();
  const navigate = useNavigate();

  const getData = async (URL: string): Promise<AuthTypes> => {
    const { data } = await axios.get(`${process.env.REACT_APP_SERVER_URL}/${URL}`);

    if (data.isMember) {
      // localStorage를 사용하지 않는 방법 고민하기
      const { jwtResponse, memberResponse } = data;
      axios.defaults.headers.common.Authorization = `Bearer ${jwtResponse.accessToken}`;
      axios.defaults.withCredentials = true;
      window.localStorage.setItem('refresh_token', JSON.stringify(jwtResponse.refreshToken));
      window.localStorage.setItem('userInfo', JSON.stringify(memberResponse));
      await setIsOAuth(true);
      navigate('/issues');
    } else {
      navigate('/sign-up');
    }

    return data;
  };

  const { isLoading, data, error } = useQuery<AuthTypes>('auth', () => getData(`api/auth${location.search}`));

  if (isLoading) return <div>로그인 중</div>;
  if (error) return <div>에러발생!</div>;
  if (!data) return <div>데이터없음</div>;

  return <div />;
};

export default Oauth;
