import React, {
  createContext,
  SetStateAction,
  useMemo,
  useState,
  Dispatch,
} from 'react';

type author = {
  id: number;
  name: string;
  profileImageUrl: string;
};

type labels = {
  id: number;
  name: string;
  color: string;
};

type assignees = {
  id?: number;
  name: string;
  profileImageUrl: string;
};

type milestone = {
  id?: number;
  title: string;
  deadline: string;
};

export type IssueType = {
  id: number;
  title: string;
  author: author;
  createdAt: string;
  milestone: milestone;
  labels: labels[];
  assignees: assignees[];
};

export type IssueData = {
  openIssueCount: number;
  closedIssueCount: number;
  issues: IssueType[];
};

type IssueDataContextType = {
  issueData: IssueData;
  setIssueData: Dispatch<SetStateAction<IssueData>>;
};

export const IssueDataContext = createContext<IssueDataContextType>({
  issueData: { closedIssueCount: 0, openIssueCount: 0, issues: [] },
  setIssueData: () => {},
});

export function IssueDataProvider({
  children,
}: {
  children: JSX.Element | JSX.Element[];
}) {
  const [issueData, setIssueData] = useState<IssueData>({
    closedIssueCount: 0,
    openIssueCount: 0,
    issues: [],
  });
  const issueDataContext = useMemo(
    () => ({ issueData, setIssueData }),
    [issueData]
  );

  return (
    <IssueDataContext.Provider value={issueDataContext}>
      {children}
    </IssueDataContext.Provider>
  );
}
