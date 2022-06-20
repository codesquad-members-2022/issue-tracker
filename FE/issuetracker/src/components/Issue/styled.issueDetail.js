import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const issueDetailWrapper = styled.div`
  ${mixin.flexbox({})};
  margin: 16px 0;
`;

export const issueLabel = styled.label`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
  width: 100px;
  height: 40px;
  border: 2px solid ${({ theme }) => theme.backgroundColors.blue2};
  border-radius: 30px;
  margin-right: 8px;
  background: ${({ theme }) => theme.backgroundColors.blue1};
  color: ${({ theme }) => theme.fontColors.blue};
`;

export const labelText = styled.span`
  ${({ theme }) => theme.fontStyles.textXSmall};
`;

export const issueDetail = styled.span`
  ${({ theme }) => theme.fontStyles.textMedium};
  color: ${({ theme }) => theme.fontColors.gray3};
`;
