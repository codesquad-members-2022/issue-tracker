import styled, { css } from 'styled-components';

interface I$FilterBar {
  isFocus: boolean;
}

const focusStyle = css`
  background-color: ${({ theme }) => theme.PALETTE.WHITE};
  border-color: ${({ theme }) => theme.PALETTE.BLACK};
`;

const $FilterBar = styled.div<I$FilterBar>`
  display: flex;
  width: 600px;
  height: 40px;
  border: 1px solid ${({ theme }) => theme.COLOR.line};
  border-radius: 11px;
  ${({ isFocus }) => isFocus && focusStyle}
`;

const $InputWrapper = styled.div`
  display: flex;
  padding: 0 24px;
  align-items: center;
  flex: 1;
  border-left: 1px solid ${({ theme }) => theme.COLOR.line};
  background-color: ${({ theme }) => theme.COLOR.inputBackground};
  :focus-within {
    background-color: ${({ theme }) => theme.PALETTE.WHITE};
  }
`;

export { $FilterBar, $InputWrapper };
