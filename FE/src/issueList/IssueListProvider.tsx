import {
  createContext,
  ReactNode,
  useContext,
  useReducer,
  Dispatch
} from 'react';
import checkReducer, { stateType, actionType } from './checkReducer';

type issueListContextType = {
  state: stateType;
  dispatch: Dispatch<actionType>;
};

const issueListInitialState: stateType = {
  headerCheckBox: 'initial',
  selectedIssues: {}
};

const issueListContext = createContext<issueListContextType>({
  state: issueListInitialState,
  dispatch: () => {}
});

export const useIssueListContext = () => useContext(issueListContext);

type IssueListProviderProps = {
  children: ReactNode;
};

function IssueListProvider({ children }: IssueListProviderProps) {
  const [state, dispatch] = useReducer(checkReducer, issueListInitialState);

  return (
    <issueListContext.Provider value={{ state, dispatch }}>
      {children}
    </issueListContext.Provider>
  );
}

export default IssueListProvider;
