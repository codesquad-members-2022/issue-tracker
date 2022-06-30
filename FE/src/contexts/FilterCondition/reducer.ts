import { Action, ActionType } from '@/contexts/FilterCondition/type';
import { IFilterCondition } from '@/types/common';

const INIT_FILTER_CONDITION: IFilterCondition = {
  status: 'OPEN',
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
    (assignee ? ` assignee:${assignee}` : '') +
    (author ? ` author:${author}` : '') +
    (label ? ` label:${label}` : '') +
    (milestone ? ` milestone:${milestone}` : '') +
    (comment ? ` comment:${comment}` : '')
  );
}

export { INIT_FILTER_CONDITION, reducer, createFilterConditionString };
