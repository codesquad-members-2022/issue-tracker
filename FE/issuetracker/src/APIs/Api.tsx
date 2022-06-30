import axios from 'axios';
import { useMutation, useQuery } from 'react-query';
import { CommentType } from 'store/newIssue';

type dataType = {
  title: string;
  content: CommentType[];
  writerId: number | undefined;
  assignees: number[];
  labels: number[];
  milestone: number;
};
export const useGetData = (key: string, URL: string) => useQuery(key, () => getData(URL));

export const usePostData = (URL: string, updatedData: dataType) => {
  const mutation = useMutation((updatedData: dataType) => postData(URL, updatedData));
  return mutation;
};

type ControlDataType = {
  [key: string]: number | string;
};

export async function usePostFetch(URL: string, updatedData: ControlDataType | undefined) {
  await axios.post(URL, updatedData);
}

export const getData = async (URL: string) => {
  const { data } = await axios.get(URL);
  return data;
};

export const postData = async (URL: string, data: any) => {
  const response = await axios.post(URL, data);
  return response;
};
