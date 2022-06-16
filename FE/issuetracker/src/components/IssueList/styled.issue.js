import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const IssueWrap = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  width: 100%;
  height: 100px;
  background: ${({ theme }) => theme.backgroundColors.gray1};
`;
