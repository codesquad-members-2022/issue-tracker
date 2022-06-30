import styled from 'styled-components';
import { Text } from '@/components/common/Text';
import CHECK_OFF_CIRCLE from '@/assets/Icons/checkOffCircle.svg';

type LabelListItemProps = {
  color: string;
  name: string;
  onClick?(e: React.MouseEvent<HTMLElement>): void;
};

const StyledListItem = styled.li`
  display: flex;
  gap: 8px;
  align-items: center;
  padding: 12px 16px;
  justify-content: space-between;
  border-top: 1px solid ${({ theme }) => theme.color.line};
`;

const LabelColorBadge = styled.div`
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background-color: ${({ color }) => color};
`;

export function LabelListItem({ color, name, onClick }: LabelListItemProps) {
  return (
    <StyledListItem data-name={name} onClick={onClick}>
      <LabelColorBadge color={color} />
      <Text text={name} styles="width: 100px" />
      <CHECK_OFF_CIRCLE />
    </StyledListItem>
  );
}
