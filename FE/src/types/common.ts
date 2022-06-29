export type IssueStatusType = 'OPEN' | 'CLOSE';

export interface IFilterCondition {
  status?: IssueStatusType;
  assignee?: string | null;
  label?: string | null;
  milestone?: string | null;
  author?: string | null;
  comment?: string | null;
}
