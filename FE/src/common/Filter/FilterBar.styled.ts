import { css } from 'styled-components';

export const FilterStyle = css<{ position: string }>`
  ${({ position }) =>
    position === 'top' &&
    css`
      top: 35px;
      left: 0;
    `}
`;
