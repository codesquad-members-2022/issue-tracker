import { ThemeProvider } from 'styled-components';
import GlobalStyles from '../src/common/global';
import theme from '../src/common/theme';

export const parameters = {
  actions: { argTypesRegex: '^on[A-Z].*' },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
};

export const decorators = [
  Story => (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <Story />
    </ThemeProvider>
  ),
];
