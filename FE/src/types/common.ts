export type IssueStatusType = 'open' | 'closed';

type IssueOption = {
  name: string;
  id: number;
} | null;

export interface IFilterCondition {
  status?: IssueStatusType;
  assignee?: IssueOption;
  label?: IssueOption;
  milestone?: IssueOption;
  author?: IssueOption;
  comment?: IssueOption;
}
