import { useState } from 'react';
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
};

export type IssueListStateType = 'opened' | 'closed' | 'all';

function IssueList() {
  const [issueListState, setIssueListState] =
    useState<IssueListStateType>('opened');
  const [headerCheckBoxType, setHeaderCheckBoxType] =
    useState<CheckBoxType>('initial');

  const initSelectedIssues = (issues: IssueType[]) => {
    const initialSelectedIssue: SelectedIssueType = {};

    issues.forEach(({ id }) => {
      initialSelectedIssue[id] = false;
    });

    return initialSelectedIssue;
  };

  const issueList =
    issueListState === 'opened'
      ? openedIssueList
      : issueListState === 'closed'
      ? closedIssueList
      : allIssueList;

  const [selectedIssues, setSelectedIssues] = useState<SelectedIssueType>(
    initSelectedIssues(issueList.issues)
  );

  const updateIssueState = (id: string) => {
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
      <div>
        {!!issueList.issues.length ? (
          issueList.issues.map(
            (
              { id, title, createdTime, writer, labels, milestoneName },
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
                isSelected={selectedIssues[id]}
                isLast={idx === issueList.issues.length - 1}
                updateIssueState={updateIssueState}
              />
            )
          )
        ) : (
          <EmptyIssueItem />
        )}
      </div>
    </>
  );
}

export default IssueList;

const openedIssueList = {
  labelCount: 3,
  milestoneCount: 2,
  openedIssues: 3,
  closedIssues: 0,
  issues: [
    {
      id: 1,
      title: '제목1',
      createdTime: '2022-06-23 12:12:13',
      writer: '글쓴이1',
      labels: [
        {
          name: '라벨네임',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF'
          }
        },
        {
          name: '라벨네임1',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF'
          }
        }
      ],
      milestoneName: '마일스톤1'
    },
    {
      id: 2,
      title: '제목2',
      createdTime: '2022-06-22 12:12:13',
      writer: '글쓴이2',
      labels: [],
      milestoneName: '마일스톤2'
    },
    {
      id: 3,
      title: '제목3',
      createdTime: '2021-06-22 20:12:13',
      writer: '글쓴이3',
      labels: [
        {
          name: '라벨네임',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF'
          }
        },
        {
          name: '라벨네임1',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF'
          }
        }
      ],
      milestoneName: ''
    }
  ]
};

const closedIssueList = {
  labelCount: 3,
  milestoneCount: 2,
  openedIssues: 3,
  closedIssues: 0,
  issues: []
};

const allIssueList = {
  labelCount: 3,
  milestoneCount: 2,
  openedIssues: 3,
  closedIssues: 0,
  issues: [
    {
      id: 1,
      title: '제목1',
      createdTime: '2022-06-23 12:12:13',
      writer: '글쓴이1',
      labels: [
        {
          name: '라벨네임',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF'
          }
        },
        {
          name: '라벨네임1',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF'
          }
        }
      ],
      milestoneName: '마일스톤1'
    },
    {
      id: 2,
      title: '제목2',
      createdTime: '2022-06-22 12:12:13',
      writer: '글쓴이2',
      labels: [],
      milestoneName: '마일스톤2'
    },
    {
      id: 3,
      title: '제목3',
      createdTime: '2021-06-22 20:12:13',
      writer: '글쓴이3',
      labels: [
        {
          name: '라벨네임',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF'
          }
        },
        {
          name: '라벨네임1',
          color: {
            backgroundColor: '#000000',
            textColor: '#FFFFFF'
          }
        }
      ],
      milestoneName: ''
    }
  ]
};
