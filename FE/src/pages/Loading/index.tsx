import Layout from '@/layout';

import queryString from 'qs';

import { Plane } from 'react-loader-spinner';
import { $Contents } from '@/pages/Loading/style';
import { useEffect } from 'react';
import { getLoginToken } from '@/api/githubOauth';
import { useNavigate } from 'react-router-dom';

export default function Loading() {
  const navigate = useNavigate();

  const getGithubToken = async () => {
    const { code } = queryString.parse(location.search, {
      ignoreQueryPrefix: true
    });

    const response = await getLoginToken({ code: code });
    const { data, status } = response;

    if (status !== 200) {
      navigate('/login', { replace: true });
    }

    localStorage.setItem('currentUserInfo', JSON.stringify(data));
    navigate('/issue-list', { replace: true });
  };

  useEffect(() => {
    getGithubToken();
  }, []);

  return (
    <Layout>
      <$Contents>
        <Plane ariaLabel="loading-indicator" />
      </$Contents>
    </Layout>
  );
}
