import { ThemeProvider } from 'styled-components';
import GlobalStyle from '../src/styles/GlobalStyle';
import * as CommonStyle from '../src/styles/common';

export const parameters = {
  actions: { argTypesRegex: '^on[A-Z].*' },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/
    }
  }
};

export const decorators = [
  Story => (
    <ThemeProvider theme={CommonStyle}>
      <GlobalStyle />
      <Story />
    </ThemeProvider>
  )
];
