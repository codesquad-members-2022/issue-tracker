import styled from 'styled-components';
import { Text } from '../common/Text';

const ListItem = styled.a`
  display: flex;
  justify-content: space-between;
  padding: 8px 16px;
  border-top: 1px solid ${({ theme }) => theme.color.line};
`;

const ListText = styled.div`
  min-width: 184px;
`;

type MilestoneFilterItemProps = {
  title: string;
};

export function MilestoneFilterItem({ title }: MilestoneFilterItemProps) {
  return (
    <ListItem>
      <ListText>
        <Text text={`${title}`} />
      </ListText>
      <Text text="⃝" />
    </ListItem>
  );
}
