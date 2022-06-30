import styled from 'styled-components';
import { Badge } from '../common/Badge';
import { Text } from '../common/Text';

type SideBarAssigneeItemProps = {
  name: string;
  profileImage: string;
};

const SideBarAssigneeItemWrapper = styled.li`
  display: flex;
  padding: 9px 32px;
  gap: 8px;
`;

export function SideBarAssigneeItem({
  name,
  profileImage,
}: SideBarAssigneeItemProps) {
  return (
    <SideBarAssigneeItemWrapper>
      <Badge imgSRC={profileImage} alt={`${name}프로필`} />
      <Text text={name} />
    </SideBarAssigneeItemWrapper>
  );
}
