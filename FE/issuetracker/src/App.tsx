import { ThemeProvider } from 'styled-components';
import { theme } from 'design/theme';
import { BrowserRouter } from 'react-router-dom';
import Router from 'Router';
import GlobalStyles from 'design/GlobalStyles';
import 'assets/style.css';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <BrowserRouter>
        <Router />
      </BrowserRouter>
    </ThemeProvider>
  );
}

export default App;
