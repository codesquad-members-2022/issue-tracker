import styled from 'styled-components';
import { Badge } from '../common/Badge';
import { Text } from '../common/Text';
import MILESTONE_ICON from '@/assets/Icons/milestone.svg';

export const IssueItemBadgesLayout = styled.div`
  display: flex;
  gap: 32px;
`;

export const BadgeField = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80px;
  padding-right: 16px;
`;

type Assignee = {
  name: string;
  profileImage: string;
};

type Milestone = {
  title: string;
};

type IssueItemBadgesProps = {
  milestone?: Milestone;
  assignee?: Assignee[];
};

export function IssueItemBadges({ milestone, assignee }: IssueItemBadgesProps) {
  return (
    <IssueItemBadgesLayout>
      <BadgeField>{milestone && <MILESTONE_ICON />}</BadgeField>

      <BadgeField>
        {assignee &&
          assignee.map(user => (
            <Badge
              badgeSize="small"
              imgSRC={user.profileImage}
              alt={user.name}
              key={`${user.name}${Date.now()}`}
            />
          ))}
      </BadgeField>
    </IssueItemBadgesLayout>
  );
}
