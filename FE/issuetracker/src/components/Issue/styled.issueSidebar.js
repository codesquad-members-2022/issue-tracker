import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

<<<<<<< HEAD
export const sidebarWrapper = styled.aside``;

export const sidebar = styled.div`
=======
export const sidebar = styled.aside`
>>>>>>> origin/11-feat-fe-newissue-레이아웃-구현
  width: 308px;
  height: 504px;
  border-radius: 16px;
  margin-left: 16px;
  background: ${({ theme }) => theme.backgroundColors.gray1};
`;

export const userBar = styled.div`
  height: 216px;
  padding: 32px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-bottom: 0;
  border-radius: 16px 16px 0 0;
`;

export const labelBar = styled.div`
  height: 140px;
  padding: 32px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-bottom: 0;
`;

export const milestoneBar = styled.div`
  height: 148px;
  padding: 32px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-radius: 0 0 16px 16px;
`;

export const barHeader = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  color: ${({ theme }) => theme.fontColors.gray2};
`;

export const barHeaderText = styled.span`
  ${({ theme }) => theme.fontStyles.linkSmall};
`;

export const userUnit = styled.div`
  ${mixin.flexbox({ vertical: 'center' })};
  height: 44px;
  margin-top: 24px;
`;

export const userName = styled.span`
  ${({ theme }) => theme.fontStyles.textSmall};
`;

export const label = styled.label`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
<<<<<<< HEAD
  ${({ theme }) => theme.fontStyles.textXSmall};
  width: 121px;
  height: 28px;
  padding: 0 16px;
  border-radius: 30px;
  margin: 16px 0;
  background: ${({ theme }) => theme.backgroundColors.purple1};
  color: ${({ theme }) => theme.fontColors.gray1};
`;

export const progressBar = styled.div`
  height: 8px;
  border-radius: 10px;
  margin-top: 16px;
  background: linear-gradient(
    90deg,
    ${({ theme }) => theme.backgroundColors.blue2} 0%,
    ${({ theme }) => theme.backgroundColors.blue2} 15.21%,
    ${({ theme }) => theme.backgroundColors.gray3} 15.22%,
    ${({ theme }) => theme.backgroundColors.gray3} 100%
  );
`;

export const milstoneContent = styled.div`
  ${({ theme }) => theme.fontStyles.textSmall};
  height: 24px;
  margin-top: 4px;
  color: ${({ theme }) => theme.fontColors.gray2};
`;

export const buttonWrapper = styled.div`
  ${mixin.flexbox({ dir: 'row-reverse' })};
  height: 32px;
`;

export const deleteButton = styled.button.attrs(() => ({ type: 'button' }))`
  width: 67px;
  height: 32px;
  margin: 12px 32px 0 0;
  color: ${({ theme }) => theme.fontColors.red};
`;

export const deleteButtonText = styled.span`
  ${({ theme }) => theme.fontStyles.linkSSmall};
`;
=======
  width: 121px;
  height: 28px;
  margin: 16px 0;
  border-radius: 30px;
  background: ${({ theme }) => theme.backgroundColors.purple1};
  color: ${({ theme }) => theme.fontColors.gray1};
`;
>>>>>>> origin/11-feat-fe-newissue-레이아웃-구현
