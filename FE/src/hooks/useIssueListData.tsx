import { getIssueList } from '@/api/issueList';
import { getIssueCount } from '@/api/issueList';
import { useQuery } from 'react-query';
import { issueListQueryKeys, issueCountQueryKeys } from '@/api/queryKeys';
import printError from '@/utils/printError';
import { IFilterCondition } from '@/contexts/FilterCondition/type';

const createIssueListQueryKey = (filterCondition: IFilterCondition) => [
  'issue',
  { ...filterCondition }
];

function useIssueListData(filterCondition: IFilterCondition) {
  return useQuery(createIssueListQueryKey(filterCondition), () => getIssueList(filterCondition), {
    onSuccess: data => {},
    onError: printError
  });
}

function useIssueCountData(filterCondition: IFilterCondition) {
  return useQuery(
    [...createIssueListQueryKey(filterCondition), 'count'],
    () => getIssueCount(filterCondition),
    {
      onSuccess: data => {},
      onError: printError
    }
  );
}

export { useIssueListData, useIssueCountData };
