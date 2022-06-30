import axios from 'axios';
import { useEffect, useState } from 'react';
import { useQuery } from 'react-query';

export const useGetFetch = (URL: string) => {
  const [fetchedData, setFetchedData] = useState<[]>();
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<boolean>();

  useEffect(() => {
    setLoading(true);
    const getDatas = async () => {
      try {
        const response = await axios.get(URL);
        setFetchedData(response.data);
        setLoading(false);
      } catch (error) {
        setLoading(false);
        setError(true);
      }
    };

    getDatas();
  }, [URL]);

  return { fetchedData, loading, error };
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

export const getLabelData = async (URL: string) => {
  const { data } = await axios.get(URL);
  return data;
};

export const useLabelQuery = (URL: string) => useQuery('labels', () => getData(URL));
