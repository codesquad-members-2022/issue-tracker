import { createRoot } from 'react-dom/client';
import { ThemeProvider } from 'styled-components';
import GlobalStyle from '@/styles/GlobalStyle';
import * as CommonStyle from '@/styles/common';

import App from './App';
import { BrowserRouter } from 'react-router-dom';

const container = document.getElementById('root');
const root = createRoot(container as Element);

root.render(
  <ThemeProvider theme={CommonStyle}>
    <GlobalStyle />
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </ThemeProvider>
);
