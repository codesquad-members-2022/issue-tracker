import { requestApi } from '@/api/core';

const getLabelList = async () => {
  const labelList = await requestApi({
    method: 'get',
    url: '/labels'
  });

  return labelList;
};

const getLabelCount = async () => {
  const labelCount = await requestApi({
    method: 'get',
    url: '/labels/counts'
  });

  return labelCount;
};

export { getLabelList, getLabelCount };
