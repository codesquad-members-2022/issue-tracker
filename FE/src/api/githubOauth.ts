import { requestApi } from '@/api/core';
const getLoginURL = async () =>
  await requestApi({
    method: 'get',
    url: '/login/oauth2/github'
  });

const getLoginToken = async (code: string) =>
  await requestApi({
    method: 'get',
    url: `/login/oauth2/github/callback?code=${code}`,
    config: { withCredentials: true }
  });

export { getLoginURL, getLoginToken };
