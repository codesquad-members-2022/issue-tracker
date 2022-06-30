import styled, { css } from 'styled-components';

type StyledColorCodeInputTypes = {
  isActive: boolean;
  isError: boolean;
};

const StyledColorCodeInput = styled.form<StyledColorCodeInputTypes>`
  position: relative;
  display: flex;
  align-items: center;
  width: fit-content;
  height: 40px;
  padding: 0px 24px;
  border-radius: 12px;

  ${({ isActive }) => {
    return isActive
      ? css`
          border: 1px solid ${({ theme }) => theme.colors.titleActive};
          background: ${({ theme }) => theme.colors.offWhite};
        `
      : css`
          border: 1px solid ${({ theme }) => theme.colors.inputBackground};
          background: ${({ theme }) => theme.colors.inputBackground};
        `;
  }}

  label {
    width: 60px;
    margin-right: 8px;
    ${({ theme }) => theme.fontStyles.textXSmall};
    color: ${({ theme }) => theme.colors.label};
  }

  input {
    width: 80px;
    padding: 0;
    border: none;
    background: transparent;
    ${({ theme }) => theme.fontStyles.textSmall};

    &:focus {
      outline: none;
    }
  }

  button {
    width: fit-content;
    height: 16px;
    padding: 0;
    background: transparent;
    border: none;
    cursor: pointer;

    svg {
      width: 14px;
      height: 14px;
    }
  }

  ${({ isError }) => {
    return (
      isError &&
      css`
        border: 1px solid ${({ theme }) => theme.colors.error.red};
        background: ${({ theme }) => theme.colors.error.lightRed};

        &::after {
          content: '6자리 HEX 코드를 입력해주세요';
          position: absolute;
          left: 24px;
          bottom: -50%;
          width: 100%;
          color: ${({ theme }) => theme.colors.error.red};
          ${({ theme }) => theme.fontStyles.textXSmall};
        }
      `
    );
  }}
`;

export default StyledColorCodeInput;
