import { css } from 'styled-components';

const TextInputActive = css`
  ${({ theme: { colors } }) => css`
    &:active {
      background-color: ${colors.offWhite};
      border: 1px solid ${colors.titleActive};
    }
  `}
`;

const TextInputFocus = css`
  ${({ theme: { colors } }) => css`
    &:focus {
      background-color: ${colors.offWhite};
      border: 1px solid ${colors.titleActive};
    }
  `}
`;

const TextInputDisable = css`
  ${({ theme: { colors } }) => css`
    &:disabled {
      background-color: ${colors.offWhite};
      border: 1px solid ${colors.titleActive};
    }
  `}
`;

const TextInputSuccess = css<{ success?: boolean }>`
  ${({ theme: { colors }, success }) =>
    success &&
    css`
      background-color: ${colors.successLight};
      border: 1px solid ${colors.success};
    `}
`;

const TextInputError = css<{ error?: boolean }>`
  ${({ theme: { colors }, error }) =>
    error &&
    css`
      background-color: ${colors.errorLight};
      border: 1px solid ${colors.error};
    `}
`;

export const TextInputStyle = css<{
  size: string;
  success?: boolean;
  error?: boolean;
}>`
  ${({ theme: { colors }, size }) =>
    size === 'lg' &&
    css`
      padding: 0 24px;
      width: 340px;
      height: 64px;
      background-color: ${colors.background};
      border-radius: 16px;

      ${TextInputActive};
      ${TextInputFocus};
      ${TextInputDisable};
      ${TextInputSuccess};
      ${TextInputError};
    `}

  ${({ theme: { colors }, size }) =>
    size === 'md' &&
    css`
      padding: 0 24px;
      width: 320px;
      height: 56px;
      background-color: ${colors.background};
      border-radius: 14px;

      ${TextInputActive};
      ${TextInputDisable};
    `}

    ${({ theme: { colors }, size }) =>
    size === 'sm' &&
    css`
      padding: 0px 24px;
      width: 300px;
      height: 40px;
      background-color: ${colors.inputBackground};
      border-radius: 11px;

      ${TextInputActive};
      ${TextInputFocus};
      ${TextInputDisable};
      ${TextInputSuccess};
      ${TextInputError};
    `}
`;
