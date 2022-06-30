import { getLabelList } from '@/api/labels';
import { getLabelCount } from '@/api/labels';
import { useQuery } from 'react-query';
import { labelListQueryKeys, labelCountQueryKeys } from '@/api/queryKeys';
import printError from '@/utils/printError';

function useLabelListData() {
  return useQuery(labelListQueryKeys, () => getLabelList(), {
    onSuccess: data => {},
    onError: printError,
    staleTime: Infinity,
    cacheTime: Infinity
  });
}

function useLabelCountData() {
  return useQuery(labelCountQueryKeys, () => getLabelCount(), {
    onSuccess: data => {},
    onError: printError,
    staleTime: Infinity,
    cacheTime: Infinity
  });
}

export { useLabelListData, useLabelCountData };
