import { useQuery } from 'react-query';
import { getServerData } from 'api/issue';

const useGetMilestoneData = () => {
  return useQuery('milestones', () => getServerData('api/milestones'), {
    cacheTime: Infinity,
  });
};

export default useGetMilestoneData;
