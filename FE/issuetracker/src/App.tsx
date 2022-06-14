import { ThemeProvider } from 'styled-components';
import { theme } from 'design/theme';
import { BrowserRouter } from 'react-router-dom';

function App() {
  return (
    <ThemeProvider theme={theme}>
      <BrowserRouter />
    </ThemeProvider>
  );
}

export default App;
