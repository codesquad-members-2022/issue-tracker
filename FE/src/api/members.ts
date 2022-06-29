import { requestApi } from '@/api/core';

const getMemberList = async () => {
  const memberList = await requestApi({
    method: 'get',
    url: '/members'
  });

  return memberList;
};

const getCurrentMember = async () => {
  const currentMember = await requestApi({
    method: 'get',
    url: '/members/current'
  });

  return currentMember;
};

export { getMemberList, getCurrentMember };
