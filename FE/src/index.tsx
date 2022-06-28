import { createRoot } from 'react-dom/client';
import { ThemeProvider } from 'styled-components';
import GlobalStyle from '@/styles/GlobalStyle';
import * as CommonStyle from '@/styles/common';

import App from './App';
import { BrowserRouter } from 'react-router-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';

const container = document.getElementById('root');
const root = createRoot(container as Element);

const queryClient = new QueryClient();

root.render(
  <QueryClientProvider client={queryClient}>
    <ReactQueryDevtools initialIsOpen={true} />
    <ThemeProvider theme={CommonStyle}>
      <GlobalStyle />
      <BrowserRouter>
        <App />
      </BrowserRouter>
    </ThemeProvider>
  </QueryClientProvider>
);
