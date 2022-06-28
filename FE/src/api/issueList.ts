import { requestApi } from '@/api/core';
import { IssueStateType } from '@/api/type';

const getIssueList = async (status: IssueStateType) => {
  const issueList = await requestApi({
    method: 'get',
    url: `/issues?${status ? `state=${status}` : ''}`
  });

  return issueList;
};

const getIssueCount = async (status: IssueStateType) => {
  const issueCount = await requestApi({
    method: 'get',
    url: `/issues/counts?${status ? `state=${status}` : ''}`
  });

  return issueCount;
};

export { getIssueList, getIssueCount };
