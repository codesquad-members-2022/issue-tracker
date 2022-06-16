import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

type LabelType = {
  title: string;
  color: string;
};
function Label({ title, color }: LabelType) {
  return <LabelWrap color={color}>{title}</LabelWrap>;
}
export const LabelWrap = styled.div<{ color: string }>`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })}
  background : ${({ color }) => color};
  height: 28px;
  min-width: 50px;
  border-radius: 999px;
  ${({ theme }) => theme.fontStyles.textXSmall};
  color: ${({ theme }) => theme.fontColors.gray1};
`;

export default Label;
