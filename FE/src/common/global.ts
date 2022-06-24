import { createGlobalStyle } from 'styled-components';
import { reset } from 'styled-reset';

const GlobalStyles = createGlobalStyle`
  ${reset}

  html {
    font-family: Noto Sans KR;
    font-size: 10px;
    background-color: ${({ theme: { colors } }) => colors.background};
  }

  textarea {
    font-family: Noto Sans KR;
  }

  input {
    font-family: Noto Sans KR;
  }

  a {
    text-decoration:none;
  }

  * {
      box-sizing:border-box;
      outline:none;
      border:none;
  }

  button {
    background-color: transparent;
  }
`;

export default GlobalStyles;
