import { useEffect, useState } from 'react';
import IssueHeader from './IssueHeader';
import IssueItem from './IssueItem';
import EmptyIssueItem from './EmptyIssueItem';
import ClickedIssueHeader from './ClickedIssueHeader';
import { useIssueListContext } from './IssueListProvider';

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
  labels: LabelType[];
  milestoneName: string;
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
} | null;

export type IssueListStateType = 'opened' | 'closed' | 'all';

function IssueList() {
  const { state, dispatch } = useIssueListContext();
  const [issueListState, setIssueListState] =
    useState<IssueListStateType>('opened');
  const [issueList, setIssueList] = useState<IssueListType>(null);

  useEffect(() => {
    fetch(`/issues?status=${issueListState}`)
      .then((res) => res.json())
      .then((data) => {
        setIssueList(data);
        dispatch({ type: 'INIT', payload: { data: data.issues } });
      });
  }, [issueListState]);

  if (!issueList) {
    return <div>Loading...</div>;
  }

  return (
    <>
      {state.headerCheckBox === 'initial' ? (
        <IssueHeader
          issueListState={issueListState}
          setIssueListState={setIssueListState}
          openedIssueCount={issueList.openedIssues}
          closedIssueCount={issueList.closedIssues}
        />
      ) : (
        <ClickedIssueHeader />
      )}
      {issueList.issues.length ? (
        issueList.issues.map(
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
              isLast={idx === issueList.issues.length - 1}
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
