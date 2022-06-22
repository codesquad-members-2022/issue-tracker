import { ThemeProvider } from 'styled-components';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from '@/login/LoginPage';
import IssueListPage from '@/issueList/IssueListPage';
import GlobalStyles from './GlobalStyles';
import theme from './theme';
import Layout from './common/Layout';

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<LoginPage />} />
          <Route path="/" element={<Layout />}>
            <Route path="issueList" element={<IssueListPage />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}
