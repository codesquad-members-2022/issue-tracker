import { createGlobalStyle } from 'styled-components';
import reset from 'styled-reset';

export const mixin = {
  flexbox: ({ dir = 'row', jc = '', ai = '' }) => `
    display: flex;
    flex-direction: ${dir};
    justify-content: ${jc};
    align-items: ${ai};
    `,

  // 사용법
  // export const MultiRangeSlider = styled.div`
  // ${mixin.flexbox({ jc: 'center', ai: 'center' })};
  // position: relative;
  // width: 100%;
  // z-index: 1;
  // `;
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
}
`;
export default GlobalStyles;
