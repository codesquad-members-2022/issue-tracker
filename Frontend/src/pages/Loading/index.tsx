import axios from 'axios';
import { useContext, useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

import { LoginStatusContext } from '@/context/LoginStatusProvider';

import * as S from './style';

const LOADING = 'Loading...';

const CALLBACK_URL = '/api/oauth/callback';

export default function Loading() {
  const location = useLocation();
  const navigate = useNavigate();
  const { loginStatus, setLoginStatus } = useContext(LoginStatusContext);

  useEffect(() => {
    // TODO: 깃헙로그인이 된 이후니까 확인이 필요 없다? 로그인 페이지에서는 별도의 리프레시 토큰 유효기간 확인이 필요 없지 않나.
    const code = location.search;
    const getToken = async (currentCode: string) => {
      try {
        const response = await axios.get(`${CALLBACK_URL}${currentCode}`);
        const newToken = response.data.accessToken;
        localStorage.setItem('token', newToken);
        setLoginStatus({ ...loginStatus, status: true });
        navigate('/main');
      } catch (error: any) {
        if (error.response) {
          console.log(error.response.status, error.response.data.message);
          // TODO: 에러 페이지로 이동 또는 로그인 페이지로 다시 이동
        }
      }
    };
    getToken(code);
  }, []);
  return (
    <S.Container>
      <S.Content>{LOADING}</S.Content>
      <S.LoadingSpinner />
    </S.Container>
  );
}
