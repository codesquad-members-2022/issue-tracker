import {
  createContext,
  ReactNode,
  useContext,
  useReducer,
  Dispatch
} from 'react';
import checkReducer, {
  IssueListCheckStateType,
  IssueListActionType
} from './checkReducer';

type IssueListContextType = {
  state: IssueListCheckStateType;
  dispatch: Dispatch<IssueListActionType>;
};

type IssueListProviderProps = {
  children: ReactNode;
};

const issueListInitialState: IssueListCheckStateType = {
  headerCheckBox: 'initial',
  selectedIssues: {}
};

const issueListContext = createContext<IssueListContextType>({
  state: issueListInitialState,
  dispatch: () => {}
});

export const useIssueListContext = () => useContext(issueListContext);

function IssueListProvider({ children }: IssueListProviderProps) {
  const [state, dispatch] = useReducer(checkReducer, issueListInitialState);

  return (
    <issueListContext.Provider value={{ state, dispatch }}>
      {children}
    </issueListContext.Provider>
  );
}

export default IssueListProvider;
