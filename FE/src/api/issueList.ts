import { requestApi } from '@/api/core';
import { APIIssueStatusType } from '@/api/type';

const getIssueList = async (status?: APIIssueStatusType) => {
  const issueList = await requestApi({
    method: 'get',
    url: `/issues${status ? `?status=${status}` : ''}`
  });

  return issueList;
};

const getIssueCount = async (status?: APIIssueStatusType) => {
  const issueCount = await requestApi({
    method: 'get',
    url: `/issues/counts${status ? `?status=${status}` : ''}`
  });

  return issueCount;
};

export { getIssueList, getIssueCount };
