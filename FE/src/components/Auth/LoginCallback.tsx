import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useQuery } from 'react-query';
import { parsedQueryType } from './AuthForm';

// backend url : http://3.34.47.60:8080/auth/github?code=${code}
// test url : https://test-234b2-default-rtdb.firebaseio.com/.json
// suspense 로딩 확인용 임시 url
const authFetcher = async (code: parsedQueryType) => {
  console.log(code);
  const response = await fetch(
    `https://test-234b2-default-rtdb.firebaseio.com/.json`,
  );

  return response.json();
};

const LoginCallBack = ({ code }: { code: parsedQueryType }) => {
  const navigate = useNavigate();
  const { status, data } = useQuery(['auth', code], () => authFetcher(code));

  if (status === 'success') {
    // localStorage.setItem('token', JSON.stringify(data));
    useEffect(() => {
      navigate('/issues');
      // react query Warning: Cannot update a component (`BrowserRouter`) while rendering a different
      // 오류 해결 관련
    }, []);
  }

  return <></>;
};

export default LoginCallBack;
