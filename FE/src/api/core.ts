import axios, { AxiosError } from 'axios';
import { AxiosType } from '@/api/type';

const baseURL = 'https://85351621-920e-4834-bd3e-7d9e2f09b244.mock.pstmn.io/api';

const instance = axios.create({
  baseURL: baseURL,
  timeout: 2000 // 응답 대기 최대 시간
});

const handleError = (err: AxiosError) => {
  if (err.response) {
    // 요청이 전송되었고, 서버는 2xx 외의 상태 코드로 응답했습니다.
    const { status, config } = err.response;
    const { baseURL = '', url = '' } = config;

    if (status === 404) {
      console.log(`${baseURL + url} not found`);
    }

    if (status === 500) {
      console.log(`Server error`);
    }
    return err.response;
  }

  if (err.request) {
    // 요청이 전송되었지만, 응답이 수신되지 않았습니다.
    console.log('Error', err.message);
  } else {
    console.log('Error', err.message);
  }

  return { data: null, status: null };
};

const requestApi = async ({ method, url, data, config }: AxiosType) => {
  const response = data
    ? await instance[method](url, data, config)
    : await instance[method](url, config)
        .then(response => response)
        .catch(handleError);
  return response;
};

export { requestApi };
