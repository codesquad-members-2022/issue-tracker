import styled, { css } from 'styled-components';

export const FlexCenterSorter = css`
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const BtnBase = css`
  ${FlexCenterSorter};

  &:hover {
    background-color: ${({ theme: { colors } }) => colors.primaryDark};
  }

  &:focus {
    border: 4px solid ${({ theme: { colors } }) => colors.primaryLight};
  }

  &:disabled {
    opacity: 0.5;
  }
`;

export const InputBase = css`
  padding: 18px 24px;
  display: block;
  width: 360px;
  height: 64px;
  border-radius: 16px;
  background-color: ${({ theme: { colors } }) => colors.inputBackground};
`;
