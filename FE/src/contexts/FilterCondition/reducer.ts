import { IFilterCondition, Action, ActionType } from '@/contexts/FilterCondition/type';

const InitFilterCondition: IFilterCondition = {
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
    case 'SET_STATE':
      return { ...state, status: payload.status };
    case 'SET_ASSIGNEE':
      return { ...state, assignee: payload.assignee };
    case 'SET_LABEL':
      return { ...state, label: payload.label };
    case 'SET_MILESTONE':
      return { ...state, milestone: payload.milestone };
    case 'SET_AUTHOR':
      return { ...state, author: payload.author };
    case 'SET_COMMENT':
      return { ...state, comment: payload.comment };
    case 'RESET':
      return InitFilterCondition;
    default:
      return state;
  }
}

export { InitFilterCondition, reducer };
