/* eslint-disable consistent-return */
import styled, { css } from 'styled-components';
import { TextStyles } from '../../common/text.styled';

export const setSNBItemBorderAndBorderRadius = (id: number) => {
  switch (id) {
    case 1:
      return css`
        border: 1px solid ${({ theme: { colors } }) => colors.line};
        border-radius: 16px 16px 0 0;
      `;
    case 2:
      return css`
        border-left: 1px solid ${({ theme: { colors } }) => colors.line};
        border-right: 1px solid ${({ theme: { colors } }) => colors.line};
      `;
    case 3:
      return css`
        border: 1px solid ${({ theme: { colors } }) => colors.line};
        border-radius: 0 0 16px 16px;
      `;
    default:
      break;
  }
};

export const Container = styled.main`
  display: flex;
`;

export const FormContainer = styled.div`
  width: 100%;
`;

export const SNB = styled.menu`
  margin-left: 32px;
  width: 308px;
  height: 96px;
`;

export const SNBItem = styled.li<{ sequence: number }>`
  background-color: ${({ theme: { colors } }) => colors.offWhite};
  ${({ sequence }) => setSNBItemBorderAndBorderRadius(sequence)}

  &:hover {
    background-color: ${({ theme: { colors } }) => colors.grey1};
  }
`;

export const SNBBtn = styled.button<{
  fontWeight: string;
  fontSize: string;
  lineHeight: string;
  color: string;
}>`
  ${TextStyles}
  padding: 34px 32px;
  width: 100%;
  height: 100%;
  display: flex;
  cursor: pointer;
`;

export const SNBBtnImg = styled.img`
  position: relative;
  margin-left: auto;
  display: block;
  width: 16px;
  height: 16px;
  top: 5px;
`;

export const User = styled.div`
  margin-right: 16px;
  width: 44px;
  height: 44px;
`;

export const UserImg = styled.img`
  width: 100%;
  height: 100%;
  object-fit: cover;
`;

export const Title = styled.input<{
  fontWeight: string;
  fontSize: string;
  lineHeight: string;
  color: string;
  isActive: boolean;
  isTitle: boolean;
}>`
  border-radius: 16px;
  background-color: ${({ theme: { colors } }) => colors.inputBackground};

  ${({ theme: { colors }, isActive }) =>
    isActive &&
    css`
      &[type='text'] {
        background-color: ${colors.offWhite};
        border: 1px solid ${colors.titleActive};
      }
    `}

  ${({ isTitle }) =>
    isTitle
      ? css`
          ${TextStyles}
          margin-bottom: 16px;
          padding: 14px 24px;
          width: 100%;
          display: block;
        `
      : css`
          display: none;
        `}
`;

export const Form = styled.form<{ isActive: boolean }>`
  height: 343px;
  display: flex;
  flex-direction: column;
  border-radius: 16px;
  background-color: ${({ theme: { colors } }) => colors.inputBackground};

  ${({ theme: { colors }, isActive }) =>
    isActive &&
    css`
      background-color: ${colors.offWhite};
      border: 1px solid ${colors.titleActive};
    `}
`;

export const TextArea = styled.textarea<{
  fontWeight: string;
  fontSize: string;
  lineHeight: string;
  color: string;
  isActive: boolean;
}>`
  ${TextStyles}
  padding: 16px 24px;
  width: 100%;
  height: 100%;
  border-radius: 16px 16px 0 0;
  resize: none;

  ${({ theme: { colors } }) => css`
    background-color: ${colors.inputBackground};
    border-bottom: 1px dashed ${colors.titleActive};
  `};

  ${({ theme: { colors }, isActive }) =>
    isActive &&
    css`
      background-color: ${colors.offWhite};
    `}
`;

export const Label = styled.label`
  ${TextStyles}
  padding: 19px 25px;
  display: flex;
`;

export const LabelImg = styled.img`
  margin-right: 10px;
  display: block;
`;

export const InputFile = styled.input`
  display: none;
`;
