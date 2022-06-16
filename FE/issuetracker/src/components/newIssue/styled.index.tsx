import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const NewIssueWrap = styled.div``;

export const NewIssueTitle = styled.div`
  ${({ theme }) => theme.fontStyles.display};
  color: ${({ theme }) => theme.fontColors.gray5};
`;

export const TitleUnderLine = styled.div`
  width: 100%;
  border-bottom: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  margin: 32px 0;
`;
export const NewIssueContent = styled.div`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'flex-start' })};
  width: 100%;
  gap: 32px;
`;
export const ImgAndTitle = styled.div`
  ${mixin.flexbox({ vertical: 'start' })};
  width: 940px;
  gap: 16px;
`;
export const TitleInput = styled.input`
  width: 880px;
  height: 56px;
  ${({ theme }) => theme.fontStyles.textSmall};
  padding: 14px 24px;
  border-radius: 14px;
  background: ${({ theme }) => theme.backgroundColors.gray3};
`;
export const AdditionalContents = styled.div`
  ${mixin.flexbox({ dir: 'column', vertical: 'flex-start' })};
  width: 308px;
  border-radius: 16px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
`;
export const AdditionalContent = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  padding: 34px 32px;
  width: 100%;
  border-top: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  ${({ theme }) => theme.fontStyles.linkSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
`;
