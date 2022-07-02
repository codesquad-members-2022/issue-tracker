import { Dispatch, SetStateAction } from 'react';

import { IssueData, IssueType } from '@/context/IssueDataProvider';

export type IssueStatus = 'OPEN' | 'CLOSED';

export type IssueMenuBarProps = {
  issueData: IssueData;
  setIssueStatus: Dispatch<SetStateAction<IssueStatus>>;
};

export type IssueListProps = {
  issueData: IssueData;
  setPageNum: Dispatch<SetStateAction<number>>;
};

export type IssueItemProps = {
  issue: IssueType;
};
