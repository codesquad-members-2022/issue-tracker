import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const IssueWrap = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  width: 100%;
  height: 100px;
  background: ${({ theme }) => theme.backgroundColors.gray1};
  padding: 24px 32px;
  border-top: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
`;
export const IssueTop = styled.div`
  ${mixin.flexbox({ horizontal: 'flex-start', vertical: 'center' })};
  gap: 9px;
`;
export const IssueLeft = styled.div`
  ${mixin.flexbox({ dir: 'column', horizontal: 'center', vertical: 'flex-start' })};
  gap: 14px;
`;
export const AlertCircleFigure = styled.figure`
  color: ${({ theme }) => theme.backgroundColors.blue2};
`;
export const Title = styled.div`
  ${({ theme }) => theme.fontStyles.linkMedium};
  color: ${({ theme }) => theme.fontColors.gray5};
`;
export const IssueBottom = styled.div`
  ${mixin.flexbox({ vertical: 'center' })};
  padding-left: 60px;
  gap: 16px;
`;
export const IssueBottomContent = styled.div`
  ${({ theme }) => theme.fontStyles.textSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
`;
