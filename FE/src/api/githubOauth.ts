import { requestApi } from '@/api/core';
const getLoginURL = async () =>
  await requestApi({
    method: 'get',
    url: '/login/oauth/github'
  });

const getLoginToken = async (data: any) =>
  await requestApi({
    method: 'get',
    url: '/login/oauth/github/callback?code=12345678',
    data,
    config: { withCredentials: true }
  });

export { getLoginURL, getLoginToken };
