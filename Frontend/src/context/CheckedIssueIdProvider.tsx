import { createContext, useMemo, useReducer } from 'react';

type Action = {
  type: string;
  id?: number;
  idList?: number[];
};

type State = Set<unknown>;

function reducer(state: State, action: Action): State {
  switch (action.type) {
    case 'check': {
      state.add(action.id);
      const newState = new Set(state);
      return newState;
    }
    case 'cancel': {
      state.delete(action.id);
      const newState = new Set(state);
      return newState;
    }
    case 'allCheck': {
      action.idList?.map((id) => state.add(id));
      const newState = new Set(state);
      return newState;
    }
    case 'allClear': {
      state.clear();
      const newState = new Set(state);
      return newState;
    }
    default: {
      throw Error;
    }
  }
}

type CheckedIssueIdContextType = {
  checkedIssueId: State;
  dispatch: React.Dispatch<any>;
};

export const CheckedIssueIdContext = createContext<CheckedIssueIdContextType>({
  checkedIssueId: new Set(),
  dispatch: () => {},
});

export function CheckedIssueIdProvider({
  children,
}: {
  children: JSX.Element | JSX.Element[];
}) {
  const [checkedIssueId, dispatch] = useReducer(reducer, new Set());

  const checkedIssueIdContext = useMemo(
    () => ({ checkedIssueId, dispatch }),
    [checkedIssueId]
  );

  return (
    <CheckedIssueIdContext.Provider value={checkedIssueIdContext}>
      {children}
    </CheckedIssueIdContext.Provider>
  );
}
