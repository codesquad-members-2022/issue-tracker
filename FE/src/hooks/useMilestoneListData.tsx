import { getMilestoneList } from '@/api/milestones';
import { getMilestoneCount } from '@/api/milestones';
import { useQuery } from 'react-query';
import { AxiosError } from 'axios';
import { milestoneListQueryKeys, milestoneCountQueryKeys } from '@/api/queryKeys';

const printError = (error: AxiosError) => {
  console.error(error.message);
};

function useMilestoneListData() {
  return useQuery(milestoneListQueryKeys, () => getMilestoneList(), {
    onSuccess: data => {},
    onError: printError
  });
}

function useMilestoneCountData() {
  return useQuery(milestoneCountQueryKeys, () => getMilestoneCount(), {
    onSuccess: data => {},
    onError: printError
  });
}

export { useMilestoneListData, useMilestoneCountData };
