import { AxiosRequestConfig, AxiosResponse } from 'axios';

type AxiosType = {
  method: 'get' | 'post' | 'put' | 'delete' | 'patch';
  url: string;
  data?: { [key: string]: string };
  config?: AxiosRequestConfig;
};

type APIIssueStatusType = 'open' | 'closed' | undefined;

type APIResponse =
  | AxiosResponse<any, any>
  | {
      data: null;
      status: null;
    };

export type { AxiosType, APIIssueStatusType, APIResponse };
