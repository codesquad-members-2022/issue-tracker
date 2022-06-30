import { getMilestoneList } from '@/api/milestones';
import { getMilestoneCount } from '@/api/milestones';
import { useQuery } from 'react-query';
import { milestoneListQueryKeys, milestoneCountQueryKeys } from '@/api/queryKeys';
import printError from '@/utils/printError';

function useMilestoneListData() {
  return useQuery(milestoneListQueryKeys, () => getMilestoneList(), {
    onSuccess: data => {},
    onError: printError,
    staleTime: Infinity,
    cacheTime: Infinity
  });
}

function useMilestoneCountData() {
  return useQuery(milestoneCountQueryKeys, () => getMilestoneCount(), {
    onSuccess: data => {},
    onError: printError,
    staleTime: Infinity,
    cacheTime: Infinity
  });
}

export { useMilestoneListData, useMilestoneCountData };
