import { useQuery } from 'react-query';
import { getServerData } from 'api/issue';

const useGetMemberData = () => {
  return useQuery('members', () => getServerData('api/members'), {
    cacheTime: Infinity,
  });
};

export default useGetMemberData;
