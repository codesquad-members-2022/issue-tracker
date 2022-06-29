import { IFilterCondition } from '@/types/common';

interface Action<T, P> {
  readonly type: T;
  readonly payload: P;
}

type DispatchType = React.Dispatch<Action<ActionType, IFilterCondition>>;

type ActionType = 'SET_CONDITION' | 'RESET';

export type { IFilterCondition, Action, DispatchType, ActionType };
