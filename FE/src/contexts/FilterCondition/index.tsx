import React, { createContext, useContext, useMemo, useReducer } from 'react';
import { INIT_FILTER_CONDITION, reducer } from '@/contexts/FilterCondition/reducer';
import { DispatchType } from './type';

const FilterConditionContext = createContext(INIT_FILTER_CONDITION);
const DispatchContext = createContext<DispatchType | undefined>(undefined);

const useFilterCondition = () => {
  const context = useContext(FilterConditionContext);

  if (!context) {
    throw new Error('Filter Condition Context must be used within IssueList');
  }

  return context;
};

const useFilterConditionDispatch = () => {
  const context = useContext(DispatchContext);

  if (!context) {
    throw new Error('Filter Condition Dispatch Context must be used within IssueList');
  }

  return context;
};

function FilterConditionProvider({ children }: { children: React.ReactNode }) {
  const [state, dispatch] = useReducer(reducer, INIT_FILTER_CONDITION);

  return (
    <DispatchContext.Provider value={dispatch}>
      <FilterConditionContext.Provider value={state}>{children}</FilterConditionContext.Provider>
    </DispatchContext.Provider>
  );
}

export { useFilterCondition, useFilterConditionDispatch, FilterConditionProvider };
