import { requestApi } from '@/api/core';
const getLoginURL = async () =>
  await requestApi({
    method: 'get',
    url: '/login/oauth/github'
  });

const getLoginToken = async () =>
  await requestApi({
    method: 'get',
    url: '/login/oauth/github/callback?code=12345678'
    // config: { withCredentials: true }  //-> 쿠키를 추가해주기 위한 옵션(현재 CORS ERROR 발생)
  });

export { getLoginURL, getLoginToken };
