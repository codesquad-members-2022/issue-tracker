import { useEffect, useState } from 'react';
import IssueHeader from './IssueHeader';
import IssueItem from './IssueItem';
import EmptyIssueItem from './EmptyIssueItem';
import ClickedIssueHeader from './ClickedIssueHeader';
import { useIssueListContext } from './IssueListProvider';
import { useQuery } from 'react-query';

type LabelColorType = {
  backgroundColor: string;
  textColor: string;
};

type LabelType = {
  name: string;
  color: LabelColorType;
};

export type IssueStateType = 'OPEN' | 'CLOSE';

export type IssueType = {
  id: number;
  title: string;
  createdTime: string;
  writer: string;
  labels: LabelType[] | null;
  milestoneName: string | null;
  status: IssueStateType;
};

export type SelectedIssueType = {
  [key: string]: boolean;
};

type IssueListType = {
  labelCount: number;
  milestoneCount: number;
  openedIssues: number;
  closedIssues: number;
  issues: IssueType[];
};

export type IssueListStateType = 'opened' | 'closed' | 'all';

const fetchIssueList = async (issueListState: IssueListStateType) => {
  const response = await fetch(`/issues?status=${issueListState}`);
  const issueListData = await response.json();

  return issueListData;
};

function IssueList() {
  const { state, dispatch } = useIssueListContext();
  const [issueListState, setIssueListState] =
    useState<IssueListStateType>('opened');

  const { isLoading, isError, data, error } = useQuery<IssueListType, Error>(
    ['issueList', issueListState],
    () => fetchIssueList(issueListState)
  );

  useEffect(() => {
    const initSelectedIssues = (issues: IssueType[]) => {
      const initialSelectedIssue: SelectedIssueType = {};

      issues.forEach(({ id }) => {
        initialSelectedIssue[id] = false;
      });

      return initialSelectedIssue;
    };
    if (data) {
      dispatch({
        type: 'INIT',
        payload: { initialSelectedIssues: initSelectedIssues(data.issues) }
      });
    }
  }, [data]);

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (isError || !data) {
    return <div>Error: {error?.message}</div>;
  }

  return (
    <>
      {state.headerCheckBox === 'initial' ? (
        <IssueHeader
          issueListState={issueListState}
          setIssueListState={setIssueListState}
          openedIssueCount={data.openedIssues}
          closedIssueCount={data.closedIssues}
        />
      ) : (
        <ClickedIssueHeader />
      )}
      {data.issues.length ? (
        data.issues.map(
          (
            { id, title, createdTime, writer, labels, milestoneName, status },
            idx
          ) => (
            <IssueItem
              key={id}
              id={String(id)}
              title={title}
              createdTime={createdTime}
              writer={writer}
              labels={labels}
              milestoneName={milestoneName}
              isLast={idx === data.issues.length - 1}
              status={status}
            />
          )
        )
      ) : (
        <EmptyIssueItem />
      )}
    </>
  );
}

export default IssueList;
