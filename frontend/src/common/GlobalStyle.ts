import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

const GlobalStyle = createGlobalStyle`
  ${reset};
  *{
    box-sizing: border-box;
    user-select: none; 
  }
  body {
    background-color: #22272E;
  }
`;

export default GlobalStyle;
