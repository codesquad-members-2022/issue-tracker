import { getLabelList } from '@/api/labels';
import { getLabelCount } from '@/api/labels';
import { useQuery } from 'react-query';
import { AxiosError } from 'axios';
import { labelListQueryKeys, labelCountQueryKeys } from '@/api/queryKeys';

const printError = (error: AxiosError) => {
  console.error(error.message);
};

function useLabelListData() {
  return useQuery(labelListQueryKeys, () => getLabelList(), {
    onSuccess: data => {},
    onError: printError
  });
}

function useLabelCountData() {
  return useQuery(labelCountQueryKeys, () => getLabelCount(), {
    onSuccess: data => {},
    onError: printError
  });
}

export { useLabelListData, useLabelCountData };
