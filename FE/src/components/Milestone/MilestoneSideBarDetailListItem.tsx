import styled from 'styled-components';
import { Text } from '@/components/common/Text';
import CHECK_OFF_CIRCLE from '@/assets/Icons/checkOffCircle.svg';

type MilestoneSideBarDetailListItemProps = {
  title: string;
};

const StyledListItem = styled.li`
  display: flex;
  gap: 8px;
  align-items: center;
  padding: 12px 16px;
  border-top: 1px solid ${({ theme }) => theme.color.line};
`;
export function MilestoneSideBarDetailListItem({
  title,
}: MilestoneSideBarDetailListItemProps) {
  return (
    <StyledListItem>
      <Text text={title} styles="width: 150px" />
      <CHECK_OFF_CIRCLE />
    </StyledListItem>
  );
}
