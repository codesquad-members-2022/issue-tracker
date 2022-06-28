interface IFilterCondition {
  status?: 'open' | 'close';
  assignee?: string | null;
  label?: string | null;
  milestone?: string | null;
  author?: string | null;
  comment?: string | null;
}

interface Action<T, P> {
  readonly type: T;
  readonly payload: P;
}

type DispatchType = React.Dispatch<Action<ActionType, IFilterCondition>>;

type ActionType =
  | 'SET_STATE'
  | 'SET_ASSIGNEE'
  | 'SET_LABEL'
  | 'SET_MILESTONE'
  | 'SET_AUTHOR'
  | 'SET_COMMENT'
  | 'RESET';

export type { IFilterCondition, Action, DispatchType, ActionType };
