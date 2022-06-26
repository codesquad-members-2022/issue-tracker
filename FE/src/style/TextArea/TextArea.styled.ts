import styled, { css } from 'styled-components';
import { TextStyles } from '../../common/text.styled';

export const Form = styled.form<{ active: boolean }>`
  width: 340px;
  height: 200px;
  display: flex;
  flex-direction: column;
  border-radius: 16px;
  background-color: ${({ theme: { colors } }) => colors.inputBackground};

  ${({ theme: { colors }, active }) =>
    active &&
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
  active: boolean;
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

  ${({ theme: { colors }, active }) =>
    active &&
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
