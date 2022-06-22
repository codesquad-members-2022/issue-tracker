import React, { useEffect } from 'react';
import { QueryClient, useQuery } from 'react-query';
import { useLocation, useNavigate } from 'react-router-dom';
import qs, { ParsedQs } from 'qs';
import LoginLoading from '@components/loginCallback/LoginLoading';
import LoginError from '@components/loginCallback/LoginError';
import { useAuthQuery } from '../../hooks/useAuthQuery';
import { useIssueQuery, preFetchIssues } from '../../hooks/useIssueQuery';

const Callback = () => {
  const navigate = useNavigate();
  const { search } = useLocation();
  const { code } = qs.parse(search, {
    ignoreQueryPrefix: true,
  });
  const { status, data: token } = useAuthQuery(code);

  if (status === 'success') {
    // localStorage.setItem('jwt', token)
    navigate('/issues');
  }

  if (status === 'loading') {
    return <LoginLoading />;
  }

  if (status === 'error') {
    return <LoginError />;
  }

  return <></>;
};

export default Callback;
