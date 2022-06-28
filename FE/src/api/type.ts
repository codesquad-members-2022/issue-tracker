import { AxiosRequestConfig } from 'axios';

type IssueStateType = 'open' | 'close' | undefined;

type AxiosType = {
  method: 'get' | 'post' | 'put' | 'delete' | 'patch';
  url: string;
  data?: { [key: string]: string };
  config?: AxiosRequestConfig;
};

export type { IssueStateType, AxiosType };
