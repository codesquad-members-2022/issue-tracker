import axios, { AxiosError, AxiosRequestConfig, AxiosResponse } from 'axios';

const baseURL = 'https://0fc30a5e-96f5-446a-8ad1-7be878b3b9cb.mock.pstmn.io/api';

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

type AxiosType = {
  method: 'get' | 'post' | 'put' | 'delete' | 'patch';
  url: string;
  data?: { [key: string]: string };
  config?: AxiosRequestConfig;
};

const requestApi = async ({ method, url, data, config }: AxiosType) => {
  const response = data
    ? await instance[method](url, data, config)
    : await instance[method](url, config)
        .then(response => {
          console.log('response success + ', response);
          return response;
        })
        .catch(handleError);
  return response;
};

export { requestApi };
