import { getMemberList } from '@/api/members';
import { getCurrentMember } from '@/api/members';
import { useQuery } from 'react-query';
import { AxiosError } from 'axios';
import { memberListQueryKeys, currentMemberQueryKeys } from '@/api/queryKeys';

const printError = (error: AxiosError) => {
  console.error(error.message);
};

function useMemberListData() {
  return useQuery(memberListQueryKeys, () => getMemberList(), {
    onSuccess: data => {},
    onError: printError,
    staleTime: Infinity,
    cacheTime: Infinity
  });
}

function useMemberCountData() {
  return useQuery(currentMemberQueryKeys, () => getCurrentMember(), {
    onSuccess: data => {},
    onError: printError,
    staleTime: Infinity,
    cacheTime: Infinity
  });
}

export { useMemberListData, useMemberCountData };
