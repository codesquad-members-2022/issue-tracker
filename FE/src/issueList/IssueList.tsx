import React, { useState } from 'react';
import IssueHeader from './IssueHeader';
import IssueItem from './IssueItem';
import EmptyIssueItem from './EmptyIssueItem';

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
};

function IssueList() {
  // key: 이슈 아이디, value: true(selected) / false

  const initSelectedIssues = (issues: IssueType[]) => {
    const initialSelectedIssue: SelectedIssueType = {};

    issues.forEach(({ id }) => {
      initialSelectedIssue[id] = false;
    });

    return initialSelectedIssue;
  };

  const [selectedIssues, setSelectedIssues] = useState<SelectedIssueType>(
    initSelectedIssues(issueList.issues),
  );
  // issueList 데이터 요청

  const updateIssueState = (id: string) => {
    selectedIssues[id] = !selectedIssues[id];
    setSelectedIssues({ ...selectedIssues });
  };

  return (
    <div>
      <IssueHeader selectedIssues={selectedIssues} />
      <div>
        {issueList.issues.map((issue, idx) => (
          <IssueItem
            key={issue.id}
            id={String(issue.id)}
            isLast={idx === issueList.issues.length - 1}
            updateIssueState={updateIssueState}
          />
        ))}
      </div>
    </div>
  );
}

export default IssueList;

const issueList = {
  labelCount: 3,
  milestoneCount: 2,
  openedIssues: 2,
  closedIssues: 0,
  issues: [
    {
      id: 1,
      title: '제목',
      createdTime: '2022-06-13 12:12:13',
      writer: '글쓴이',
      labels: [
        {
          name: '라벨네임',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF',
          },
        },
        {
          name: '라벨네임1',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF',
          },
        },
      ],
      milestoneName: '마일스톤',
    },
    {
      id: 2,
      title: '제목',
      createdTime: '2022-06-13 12:12:13',
      writer: '글쓴이',
      labels: [
        {
          name: '라벨네임',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF',
          },
        },
        {
          name: '라벨네임1',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF',
          },
        },
      ],
      milestoneName: '마일스톤',
    },
    {
      id: 3,
      title: '제목',
      createdTime: '2022-06-13 12:12:13',
      writer: '글쓴이',
      labels: [
        {
          name: '라벨네임',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF',
          },
        },
        {
          name: '라벨네임1',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF',
          },
        },
      ],
      milestoneName: '마일스톤',
    },
  ],
};
