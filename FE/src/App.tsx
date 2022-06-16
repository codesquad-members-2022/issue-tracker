import React from 'react';
import { ThemeProvider } from 'styled-components';
import GlobalStyles from './GlobalStyles';
import { theme } from './theme';
import Icon from '@/assets/icons/Icon';

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <div>test</div>
      <Icon iconName={'archive'} />
    </ThemeProvider>
  );
}
