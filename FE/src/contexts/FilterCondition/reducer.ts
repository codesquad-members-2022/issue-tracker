import { Action, ActionType } from '@/contexts/FilterCondition/type';
import { IFilterCondition } from '@/types/common';

const INIT_FILTER_CONDITION: IFilterCondition = {
  status: 'open',
  assignee: null,
  label: null,
  milestone: null,
  author: null,
  comment: null
};

function reducer(
  state: IFilterCondition,
  action: Action<ActionType, IFilterCondition>
): IFilterCondition {
  const { type, payload } = action;

  switch (type) {
    case 'SET_CONDITION':
      return { ...state, ...payload };
    case 'RESET':
      return INIT_FILTER_CONDITION;
    default:
      return state;
  }
}

function createFilterConditionString({
  status,
  assignee,
  author,
  label,
  milestone,
  comment
}: IFilterCondition) {
  return (
    `is:${status?.toLowerCase()}` +
    ` is:issue` +
    (assignee ? ` assignee:${assignee.name}` : '') +
    (author ? ` author:${author.name}` : '') +
    (label ? ` label:${label.name}` : '') +
    (milestone ? ` milestone:${milestone.name}` : '') +
    (comment ? ` comment:${comment.name}` : '')
  );
}

function createFilterConditionQueryString({
  status,
  assignee,
  author,
  label,
  milestone,
  comment
}: IFilterCondition) {
  return (
    `status=${status}` +
    (assignee ? `&assigneeId=${assignee.id}` : '') +
    (author ? `&authorId=${author.id}` : '') +
    (label ? `&labelIds=${label.id}` : '') +
    (milestone ? `&milestoneId=${milestone.id}` : '') +
    (comment ? `&commenterId=${comment.id}` : '')
  );
}

export {
  INIT_FILTER_CONDITION,
  reducer,
  createFilterConditionString,
  createFilterConditionQueryString
};
