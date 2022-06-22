import { ThemeProvider } from 'styled-components';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from '@/login/LoginPage';
import IssueListPage from '@/issueList/IssueListPage';
import GlobalStyles from './GlobalStyles';
import theme from './theme';

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<LoginPage />} />
          <Route path="issueList" element={<IssueListPage />} />
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}
