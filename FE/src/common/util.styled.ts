import { css } from 'styled-components';

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
