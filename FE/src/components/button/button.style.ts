import styled, { css, FlattenInterpolation, ThemeProps } from 'styled-components';

type ButtonStyleType =
  | 'Large'
  | 'Small-Standard'
  | 'Medium-Standard'
  | 'Small-Secondary'
  | 'Medium-Text'
  | 'Small-Text';

const standardEventStyle = css`
  :hover {
    background: ${({ theme }) => theme.COLOR.primary.hover};
  }
  :active {
    background: ${({ theme }) => theme.COLOR.primary.initial};
    border: 4px solid ${({ theme }) => theme.COLOR.primary.focus};
  }
  :disabled {
    background: ${({ theme }) => theme.COLOR.primary.initial};
    opacity: 50%;
  }
`;

const textEventStyle = css`
  :hover {
    color: ${({ theme }) => theme.COLOR.body};
    svg {
      path {
        stroke: ${({ theme }) => theme.COLOR.body};
      }
    }
  }
  :active {
    color: ${({ theme }) => theme.COLOR.title};
    svg {
      path {
        stroke: ${({ theme }) => theme.COLOR.title};
      }
    }
  }
  :disabled {
    color: ${({ theme }) => theme.COLOR.body};
    opacity: 50%;
  }
`;

const large = css`
  width: 340px;
  height: 64px;
  color: ${({ theme }) => theme.PALETTE.WHITE};
  background: ${({ theme }) => theme.COLOR.primary.initial};
  font-size: ${({ theme }) => `${theme.FONT.SIZE.MEDIUM}`};
  font-weight: ${({ theme }) => `${theme.FONT.WEIGHT.BOLD}`};
  border-radius: 20px;
  ${standardEventStyle}
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
  ${standardEventStyle}
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
  ${textEventStyle}
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
