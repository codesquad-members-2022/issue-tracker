import { DispatchType } from '@/contexts/FilterCondition/type';
import { IFilterCondition } from '@/types/common';

export const setCondition = (dispatch: DispatchType, condition: IFilterCondition) => {
  dispatch({ type: 'SET_CONDITION', payload: { ...condition } });
};

export const reset = (dispatch: DispatchType) => {
  dispatch({ type: 'RESET', payload: {} });
};
