import React from 'react';
import { ThemeProvider } from 'styled-components';

import { LoginStatusProvider } from '@/context/LoginStatusProvider';

import Router from './router';
import GlobalStyle from './style/global';
import theme from './style/theme';

export default function App() {
  return (
    <div className="app">
      <ThemeProvider theme={theme}>
        <LoginStatusProvider>
          <GlobalStyle />
          <Router />
        </LoginStatusProvider>
      </ThemeProvider>
    </div>
  );
}
