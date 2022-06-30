import styled, { css } from 'styled-components';

interface StyledPageButtonTypes {
  focus: boolean;
}

export const StyledPagination = styled.div`
  display: flex;
  justify-content: center;
  padding: 24px 0;
  gap: 10px;

  & > button:first-child,
  & > button:last-child {
    &:hover:not([disabled]) {
      color: ${({ theme }) => theme.colors.primary.blue};
    }
  }
`;

export const StyledPageButton = styled.button<StyledPageButtonTypes>`
  width: 36px;
  height: 36px;
  border: none;
  border-radius: 8px;
  background: none;
  cursor: pointer;

  ${({ focus }) =>
    focus
      ? css`
          background: ${({ theme }) => theme.colors.primary.blue};
          color: ${({ theme }) => theme.colors.offWhite};
        `
      : css`
          &:hover {
            background: ${({ theme }) => theme.colors.line};
          }
        `}
`;
