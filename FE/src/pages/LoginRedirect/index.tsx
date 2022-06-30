import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import * as S from './style';

export function Callback() {
  const navigate = useNavigate();
  const code = new URL(window.location.href).searchParams.get('code');
  // TODO: 토큰 쿠키에저장
  useEffect(() => {
    fetch(`${process.env.JWT_TOKEN_URL}${code}`)
      .then(data => data.json())
      .then(() => navigate('/issueList/open'));
  }, []);

  return <S.Callback />;
}
