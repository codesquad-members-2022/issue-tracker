import styled from 'styled-components';
import { IssueItemBadges } from './IssueItemBadges';
import { IssueItemInfo } from './IssueItemInfo';

export const IssueItemLayout = styled.li`
  display: flex;
  padding: 24px 32px;
  border-top: 1px solid ${({ theme }) => theme.color.line};
  justify-content: space-between;
`;

type Label = {
  name: string;
  color: string;
  textColor?: string;
};

type Milestone = {
  title: string;
};

type Assignee = {
  name: string;
  profileImage: string;
};

export type IssueItemProps = {
  issueTitle: string;
  issueNumber: number;
  issueWriter: string;
  timestamp: string;
  milestone: Milestone;
  label: Label[];
  assignee?: Assignee[];
  open?: boolean;
};

export function IssueItem({
  issueTitle,
  issueNumber,
  issueWriter,
  timestamp,
  milestone,
  label,
  assignee,
  open,
}: IssueItemProps) {
  return (
    <IssueItemLayout>
      <IssueItemInfo
        issueTitle={issueTitle}
        issueNumber={issueNumber}
        issueWriter={issueWriter}
        timestamp={timestamp}
        milestone={milestone}
        label={label}
      />
      <IssueItemBadges milestone={milestone} assignee={assignee} />
    </IssueItemLayout>
  );
}
