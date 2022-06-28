import React, { useEffect } from 'react';
import { QueryClient, useQuery } from 'react-query';
import { useLocation, useNavigate } from 'react-router-dom';
import qs from 'qs';
import LoginLoading from '@components/loginCallback/LoginLoading';
import LoginError from '@components/loginCallback/LoginError';
import { useAuthQuery } from '../../hooks/useAuthQuery';

const Callback = () => {
  const navigate = useNavigate();
  const { search } = useLocation();
  const { code } = qs.parse(search, {
    ignoreQueryPrefix: true,
  });
  const { status, data: token } = useAuthQuery(code);

  if (status === 'success') {
    // // localStorage.setItem('jwt', token)
    // 아이디를 받고, 아이디를 넘겨주어야 함.
    navigate(`/issues?id=${'test'}`);
  }

  if (status === 'loading') {
    navigate('/issues');
    return <LoginLoading />;
  }

  if (status === 'error') {
    return <LoginError />;
  }

  return <></>;
};

export default Callback;
