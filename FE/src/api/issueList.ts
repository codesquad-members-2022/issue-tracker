import { IFilterCondition } from './../types/common';
import { requestApi } from '@/api/core';
import { createFilterConditionQueryString } from '@/contexts/FilterCondition/reducer';

const getIssueList = async (filterCondition: IFilterCondition) => {
  const issueList = await requestApi({
    method: 'get',
    url: `/issues?${createFilterConditionQueryString(filterCondition)}`
  });

  return issueList;
};

const getIssueCount = async (filterCondition: IFilterCondition) => {
  const issueCount = await requestApi({
    method: 'get',
    url: `/issues?${createFilterConditionQueryString(filterCondition)}`
  });

  return issueCount;
};

export { getIssueList, getIssueCount };
