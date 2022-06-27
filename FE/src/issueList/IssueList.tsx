import { useEffect, useState } from 'react';
import IssueHeader from './IssueHeader';
import IssueItem from './IssueItem';
import EmptyIssueItem from './EmptyIssueItem';
import { CheckBoxType } from './CheckBox';

type LabelColorType = {
  backgroundColor: string;
  textColor: string;
};

type LabelType = {
  name: string;
  color: LabelColorType;
};

type IssueType = {
  id: number;
  title: string;
  createdTime: string;
  writer: string;
  labels: LabelType[];
  milestoneName: string;
};

export type SelectedIssueType = {
  [key: string]: boolean;
} | null;

type IssueListType = {
  labelCount: number;
  milestoneCount: number;
  openedIssues: number;
  closedIssues: number;
  issues: IssueType[];
} | null;

export type IssueListStateType = 'opened' | 'closed' | 'all';

function IssueList() {
  const [issueListState, setIssueListState] =
    useState<IssueListStateType>('opened');
  const [headerCheckBoxType, setHeaderCheckBoxType] =
    useState<CheckBoxType>('initial');
  const [issueList, setIssueList] = useState<IssueListType>(null);
  const [selectedIssues, setSelectedIssues] = useState<SelectedIssueType>(null);

  useEffect(() => {
    fetch(`/issues?status=${issueListState}`)
      .then((res) => res.json())
      .then((data) => {
        setIssueList(data);
        setSelectedIssues(initSelectedIssues(data.issues));
      });
  }, [issueListState]);

  const initSelectedIssues = (issues: IssueType[]) => {
    const initialSelectedIssue: SelectedIssueType = {};

    issues.forEach(({ id }) => {
      initialSelectedIssue[id] = false;
    });

    return initialSelectedIssue;
  };

  const updateIssueState = (id: string) => {
    if (!selectedIssues) return;
    selectedIssues[id] = !selectedIssues[id];
    setSelectedIssues({ ...selectedIssues });

    const selectedIssuesCount = Object.values(selectedIssues).filter(
      (isSelected) => isSelected
    ).length;
    if (selectedIssuesCount === Object.keys(selectedIssues).length) {
      setHeaderCheckBoxType('active');
    } else if (selectedIssuesCount) {
      setHeaderCheckBoxType('disable');
    } else {
      setHeaderCheckBoxType('initial');
    }
  };

  if (!issueList) {
    return <div>Loading...</div>;
  }

  return (
    <>
      <IssueHeader
        issueListState={issueListState}
        setIssueListState={setIssueListState}
        openedIssueCount={issueList.openedIssues}
        closedIssueCount={issueList.closedIssues}
        selectedIssues={selectedIssues}
        setSelectedIssues={setSelectedIssues}
        headerCheckBoxType={headerCheckBoxType}
        setHeaderCheckBoxType={setHeaderCheckBoxType}
      />

      {issueList?.issues.length ? (
        issueList.issues.map(
          ({ id, title, createdTime, writer, labels, milestoneName }, idx) => (
            <IssueItem
              key={id}
              id={String(id)}
              title={title}
              createdTime={createdTime}
              writer={writer}
              labels={labels}
              milestoneName={milestoneName}
              isSelected={selectedIssues[id]}
              isLast={idx === issueList.issues.length - 1}
              updateIssueState={updateIssueState}
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
