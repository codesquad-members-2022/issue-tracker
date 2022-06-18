import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const IssuesWrap = styled.div`
  width: 100%;
  border-radius: 16px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  padding-bottom: 11px;
  background: ${({ theme }) => theme.backgroundColors.gray1};
`;
export const IssuesTab = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  width: 100%;
  height: 64px;
  background: ${({ theme }) => theme.backgroundColors.gray2};
  border-bottom: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-radius: 16px 16px 0px 0px;
  padding: 0 32px;
`;
export const IssuesTabLeft = styled.div`
  ${mixin.flexbox({ vertical: 'center' })};
`;
export const CheckBox = styled.input`
  width: 16px;
  height: 16px;
  margin-right: 32px;
`;
export const OpenedIssue = styled.div`
  ${({ theme }) => theme.fontStyles.linkSmall};
  color: ${({ theme }) => theme.fontColors.gray5};
  margin-right: 24px;
  gap: 5px;
`;
export const ClosedIssue = styled(OpenedIssue)``;
export const IssuesTabRight = styled(IssuesTabLeft)`
  justify-content: flex-end;
  gap: 32px;
`;
export const DropboxContent = styled.div`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
  gap: 8px;
  ${({ theme }) => theme.fontStyles.linkSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
`;
