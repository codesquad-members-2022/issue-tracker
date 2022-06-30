import styled, { css } from 'styled-components';
import { InputTypes } from 'components/Atoms/Input';

type FormStyleTypes = Pick<InputTypes, 'inputSize' | 'isActive' | 'inputStyle'>;

export const Form = styled.form<FormStyleTypes>`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  border: none;
  border-radius: 16px;
  background: ${({ theme }) => theme.colors.inputBackground};

  svg {
    margin: 0px 12px 0px 26px;
  }

  label {
    ${({ theme }) => theme.fontStyles.textXSmall}
  }

  ${({ inputStyle, inputSize }) => {
    if (inputStyle === 'FILTERBAR')
      return css`
        flex-direction: row;
        align-items: center;
        width: 472px;
        height: 40px;
        border: 1px solid ${({ theme }) => theme.colors.line};
        border-left: none;
      `;
    // eslint-disable-next-line default-case
    switch (inputSize) {
      case 'LARGE':
        return css`
          padding: 0 24px;
          ${({ theme }) => theme.textInputSize.large};
        `;
      case 'MEDIUM':
        return css`
          padding: 0 24px;
          ${({ theme }) => theme.textInputSize.medium};
        `;
      case 'SMALL':
        return css`
          flex-direction: row;
          align-items: center;
          padding: 0 24px;
          ${({ theme }) => theme.textInputSize.small};

          label {
            width: 80px;
          }
        `;
    }
  }}

  ${({ isActive, inputStyle }) =>
    isActive &&
    css`
      color: ${({ theme }) => theme.colors.placeholder};
      background: ${({ theme }) => theme.colors.offWhite};
      border: 1px solid ${({ theme }) => theme.colors.titleActive};
      ${inputStyle === 'FILTERBAR' && `border-left:none;`}
    `}
  
  &:disabled {
    opacity: 0.5;
    cursor: default;
  }

  &::placeholder {
    color: ${({ theme }) => theme.colors.placeholder};
  }

  &:focus {
    color: ${({ theme }) => theme.colors.placeholder};
    background: ${({ theme }) => theme.colors.offWhite};
    outline: 1px solid ${({ theme }) => theme.colors.titleActive};
  }
`;

export const StyledInput = styled.input`
  width: 100%;
  background: transparent;
  border: transparent;
  padding: 0;

  ${({ theme }) => theme.fontStyles.textSmall};

  color: ${({ theme }) => theme.colors.titleActive};

  &:focus {
    outline: none;
  }

  &:disabled {
    opacity: 0.5;
    cursor: default;
  }

  &::placeholder {
    color: ${({ theme }) => theme.colors.placeHolder};
  }
`;
