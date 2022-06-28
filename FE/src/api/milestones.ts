import { requestApi } from '@/api/core';

const getMilestoneList = async () => {
  const milestoneList = await requestApi({
    method: 'get',
    url: '/milestones'
  });

  return milestoneList;
};

const getMilestoneCount = async () => {
  const milestoneCount = await requestApi({
    method: 'get',
    url: '/milestones/counts'
  });

  return milestoneCount;
};

export { getMilestoneList, getMilestoneCount };
