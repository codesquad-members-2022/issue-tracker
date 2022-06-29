import { IssueStatusType } from '@/types/common';

interface IMember {
  id: number;
  image_url: string;
  user_id: string;
  name: string;
}

interface ILabel {
  id: number;
  name: string;
  color_code: string;
  description: string;
}

interface IMilestone {
  id: number;
  name: string;
  status: IssueStatusType;
  description: string;
  target_date: string;
  open_issue: number;
  closed_issue: number;
}

interface IListItem {
  id: number;
  title: string;
  status: IssueStatusType;
  assignees: IMember[];
  author: IMember;
  created_at: string;
  labels: ILabel[];
  milestone: IMilestone;
}

interface MockData {
  issueList: IListItem[];
  labelList: ILabel[];
  milestoneList: IMilestone[];
}

interface I$Text {
  size: 'large' | 'small';
}

export type { IListItem, IMember, ILabel, IMilestone, MockData, I$Text };
