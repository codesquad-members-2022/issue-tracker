import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const inputComment = styled.div`
  width: 880px;
  margin-top: 40px;
`;

export const commentWrapper = styled.div`
  ${mixin.flexbox({})};
  margin-bottom: 16px;
`;

export const commentUserImage = styled.figure`
  display: inline-flex;
  width: 44px;
  height: 44px;
  margin-right: 16px;
`;

export const comment = styled.div<{ isInputActive: boolean }>`
  ${mixin.flexbox({ dir: 'column' })};
  height: 343px;
  border-radius: 16px;
  background: ${({ isInputActive, theme }) =>
    isInputActive ? theme.backgroundColors.gray1 : theme.backgroundColors.gray3};
`;

export const textArea = styled.textarea`
  ${({ theme }) => theme.fontStyles.textSmall};
  width: 880px;
  height: 291px;
  padding: 16px 24px;
  border-radius: 16px 16px 0 0;
  color: ${({ theme }) => theme.fontColors.gray5};

  &:empty:before {
    content: attr(placeholder);
    color: ${({ theme }) => theme.fontColors.gray4};
  }
`;

export const attachFileButtonWrapper = styled.div`
  height: 51px;
  padding: 16px 24px;
  border-top: 1px dashed ${({ theme }) => theme.backgroundColors.gray4};
  border-radius: 0 0 16px 16px;
`;

export const attachFileButton = styled.button.attrs(() => ({ type: 'button' }))`
  ${({ theme }) => theme.fontStyles.linkSSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
`;

export const attachFileButtonText = styled.span`
  ${({ theme }) => theme.fontStyles.linkSSmall};
  margin-left: 8px;
`;

export const saveButtonWrapper = styled.div`
  ${mixin.flexbox({ dir: 'row-reverse' })};
  width: 100%;
  margin-left: 60px;
`;
