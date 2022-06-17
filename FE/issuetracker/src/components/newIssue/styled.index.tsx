import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const NewIssueWrap = styled.div`
  height: 150vh;
`;

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
export const AdditionalContent = styled.div<{ idx: number }>`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  padding: 34px 32px;
  width: 100%;
  border-top: ${({ idx }) => (idx === 0 ? 'none' : '1px solid #D9DBE9')};
  ${({ theme }) => theme.fontStyles.linkSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
`;
export const InputArea = styled.div`
  width: 880px;
  /* height: 343px; */
  border-radius: 16px;
  background: ${({ theme }) => theme.backgroundColors.gray3};
  padding: 16px 24px 0 24px;
  margin: 16px 0 0 64px;
`;
export const NewIssueInput = styled.textarea`
  width: 100%;
  height: 280px;
  ${({ theme }) => theme.fontStyles.textSmall};
  color: ${({ theme }) => theme.fontColors.gray4};
`;
export const IssueContentLeft = styled.div`
  width: 940px;
`;
export const AttatchFile = styled.div`
  width: 100%;
  height: 52px;
  ${mixin.flexbox({ horizontal: 'flex-start', vertical: 'center' })};
  gap: 10px;
  ${({ theme }) => theme.fontStyles.linkSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
  border-top: 1px dashed ${({ theme }) => theme.backgroundColors.gray4};
  margin-top: 10px;
`;
