import { ThemeProvider } from 'styled-components';
import { theme } from 'design/theme';
import { BrowserRouter } from 'react-router-dom';
import Router from 'Router';
import GlobalStyles from 'design/GlobalStyles';
<<<<<<< HEAD
import 'assets/style.css';
=======
>>>>>>> 61d6b972aae07b29907b94fa937dd6eaeb394cdf

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
