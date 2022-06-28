import { requestApi } from '@/api/core';
import { IssueStateType } from '@/api/type';

const getIssueList = async (state: IssueStateType) => {
  const issueList = await requestApi({
    method: 'get',
    url: `/issues?state=${state}`
  });
  return issueList;
};

const getIssueCount = async (state: IssueStateType) => {
  const issueCount = await requestApi({
    method: 'get',
    url: `/issues/counts?state=${state}`
  });
  return issueCount;
};

export { getIssueList, getIssueCount };
