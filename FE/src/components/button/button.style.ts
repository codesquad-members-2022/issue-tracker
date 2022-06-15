import styled, { css } from 'styled-components';

interface Styled_button {
  width: string;
}

const Styled_button = styled.button<Styled_button>`
  min-width: ${({ width }) => width};
  height: 100px;
  border: 1px solid black;
`;

export { Styled_button };
