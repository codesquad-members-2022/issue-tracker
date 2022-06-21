import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const issueButton = styled.button.attrs(() => ({ type: 'button' }))`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
  width: 120px;
  height: 40px;
  border: 2px solid ${({ theme }) => theme.backgroundColors.blue2};
  border-radius: 11px;
  margin-left: 8px;
  background: ${({ theme }) => theme.backgroundColors.gray1};
  color: ${({ theme }) => theme.fontColors.blue};
`;

export const buttonText = styled.span`
  ${({ theme }) => theme.fontStyles.linkSSmall};
  margin-left: 4px;
`;
