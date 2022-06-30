import { getMemberList } from '@/api/members';
import { getCurrentMember } from '@/api/members';
import { useQuery } from 'react-query';
import { memberListQueryKeys, currentMemberQueryKeys } from '@/api/queryKeys';
import printError from '@/utils/printError';

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
