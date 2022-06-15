import styled, { css, FlattenInterpolation, ThemeProps } from 'styled-components';

type ButtonStyleType =
  | 'Large'
  | 'Small-Standard'
  | 'Medium-Standard'
  | 'Small-Secondary'
  | 'Medium-Text'
  | 'Small-Text';

const large = css`
  width: 340px;
  height: 64px;
  color: ${({ theme }) => theme.PALETTE.WHITE};
  background: ${({ theme }) => theme.COLOR.primary.initial};
  font-size: ${({ theme }) => `${theme.FONT.SIZE.MEDIUM}`};
  font-weight: ${({ theme }) => `${theme.FONT.WEIGHT.BOLD}`};
  border-radius: 20px;
`;

const smallStandard = css`
  width: 120px;
  height: 40px;
  color: ${({ theme }) => theme.PALETTE.WHITE};
  background: ${({ theme }) => theme.COLOR.primary.initial};
  font-size: ${({ theme }) => `${theme.FONT.SIZE.X_SMALL}`};
  font-weight: ${({ theme }) => `${theme.FONT.WEIGHT.BOLD}`};
  border-radius: 10px;
  svg {
    path {
      stroke: ${({ theme }) => theme.PALETTE.WHITE};
    }
  }
import styled, { css } from 'styled-components';
`;

const mediumText = css`
  width: 87px;
  height: 32px;
  color: ${({ theme }) => theme.COLOR.label};
  svg {
    path {
      stroke: ${({ theme }) => theme.COLOR.label};
    }
  }
`;
interface Styled_button {
  width: string;
}

const Styled_button = styled.button<Styled_button>`
  min-width: ${({ width }) => width};
  height: 100px;
  border: 1px solid black;
`;

export { Styled_button };
