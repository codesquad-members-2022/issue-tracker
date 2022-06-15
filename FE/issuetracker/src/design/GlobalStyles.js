import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

export const mixin = {
  flexbox: ({ dir = 'row', horizontal = '', vertical = '' }) => `
    display: flex;
    flex-direction: ${dir};
    justify-content: ${horizontal};
    align-items: ${vertical};
    `,
};

const GlobalStyles = createGlobalStyle`
    ${reset}
    * {
        background: none;
        border:none;
        box-sizing:border-box;
        margin:0;
        padding:0;
        font-family: 'Noto Sans KR';
    body{
      padding: 0 80px;
      background : #F7F7FC;
    }
}
`;
export default GlobalStyles;
