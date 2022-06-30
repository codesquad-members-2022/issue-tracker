import { getIssueList } from '@/api/issueList';
import { getIssueCount } from '@/api/issueList';
import { APIIssueStatusType } from '@/api/type';
import { useQuery } from 'react-query';
import { issueListQueryKeys, issueCountQueryKeys } from '@/api/queryKeys';
import printError from '@/utils/printError';

function useIssueListData(status?: APIIssueStatusType) {
  return useQuery(issueListQueryKeys[status ? status : 'all'], () => getIssueList(status), {
    onSuccess: data => {},
    onError: printError
  });
}

function useIssueCountData(status?: APIIssueStatusType) {
  return useQuery(issueCountQueryKeys[status ? status : 'all'], () => getIssueCount(status), {
    onSuccess: data => {},
    onError: printError
  });
}

export { useIssueListData, useIssueCountData };
