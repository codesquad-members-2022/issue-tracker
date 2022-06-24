interface IAuthor {
  userId: string;
  name: string;
  profile: string;
}

interface IListItem {
  title: string;
  labelList: string[];
  number: number;
  author: IAuthor;
  timestamp: string;
  milestone: string;
  status: 'OPEN' | 'CLOSE';
}

interface ILabel {
  id: number;
  name: string;
  description: string;
  color_code: string;
}

interface IMilestone {
  id: number;
  name: string;
  description: string;
  target_date: string;
  open_issue: number;
  closed_issue: number;
}

interface MockData {
  issueList: IListItem[];
  labelList: ILabel[];
  milestoneList: IMilestone[];
}

interface I$Text {
  size: 'large' | 'small';
}

export type { IListItem, ILabel, IMilestone, MockData, I$Text };
