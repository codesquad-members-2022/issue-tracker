import { DispatchType } from '@/contexts/FilterCondition/type';

export const setStatus = (dispatch: DispatchType, status: 'open' | 'close') => {
  dispatch({ type: 'SET_STATE', payload: { status } });
};

export const setAssignee = (dispatch: DispatchType, assignee: string) => {
  dispatch({ type: 'SET_ASSIGNEE', payload: { assignee } });
};

export const setLabel = (dispatch: DispatchType, label: string) => {
  dispatch({ type: 'SET_LABEL', payload: { label } });
};

export const setMilestone = (dispatch: DispatchType, milestone: string) => {
  dispatch({ type: 'SET_MILESTONE', payload: { milestone } });
};

export const setAuthor = (dispatch: DispatchType, author: string) => {
  dispatch({ type: 'SET_AUTHOR', payload: { author } });
};

export const setComment = (dispatch: DispatchType, comment: string) => {
  dispatch({ type: 'SET_COMMENT', payload: { comment } });
};

export const reset = (dispatch: DispatchType) => {
  dispatch({ type: 'RESET', payload: {} });
};
