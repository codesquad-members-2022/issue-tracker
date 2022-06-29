import React from 'react';
import ReactDOM from 'react-dom/client';
import { ThemeProvider } from 'styled-components';
import { BrowserRouter } from 'react-router-dom';
import theme from 'common/theme';
import GlobalStyles from 'common/global';
import App from './App';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement,
);

root.render(
<<<<<<< HEAD
  <ThemeProvider theme={theme}>
    <GlobalStyles />
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </ThemeProvider>,
=======
  <React.StrictMode>
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </ThemeProvider>
  </React.StrictMode>,
>>>>>>> b0c81c97947a3b829ec8e8b1fdb15814ec70f56a
);
