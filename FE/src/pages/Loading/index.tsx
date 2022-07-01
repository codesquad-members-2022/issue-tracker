import Layout from '@/layout';

import queryString from 'qs';

import { Plane } from 'react-loader-spinner';
import { $Contents } from '@/pages/Loading/style';
import { useEffect } from 'react';
import { getLoginToken } from '@/api/githubOauth';
import { useNavigate } from 'react-router-dom';
import ROUTE_URL from '@/router/routeUrl';

export default function Loading() {
  const navigate = useNavigate();

  const getGithubToken = async () => {
    const { code } = queryString.parse(location.search, {
      ignoreQueryPrefix: true
    });

    const response = await getLoginToken(code + '');
    const { data, status } = response;

    if (status !== 200) {
      navigate(`/${ROUTE_URL.LOGIN}`, { replace: true });
    }

    const { current_user, login_token } = data;

    if (current_user && login_token) {
      localStorage.setItem('currentUserInfo', JSON.stringify(current_user));
      localStorage.setItem('currentUserToken', login_token);
      navigate(`/${ROUTE_URL.ISSUE_LIST}`, { replace: true });
    }

    navigate(`/${ROUTE_URL.LOGIN}`, { replace: true });
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
