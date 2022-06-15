import { ThemeProvider } from 'styled-components';
import GlobalStyle from '@/styles/GlobalStyle';
import * as CommonStyle from '@/styles/common';

const App = () => {
  return (
    <ThemeProvider theme={CommonStyle}>
      <GlobalStyle />
    </ThemeProvider>
  );
};
export default App;
