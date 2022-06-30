import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';
import { RecoilRoot } from 'recoil';
import { GlobalStyle } from '@/styles/GlobalStyle';
import { LoginPage } from '@/pages/Login';
import { theme } from '@/styles/theme';
import { Callback } from '@/pages/LoginRedirect';
import { IssueListPage } from '@/pages/IssueList';
import { AddIssuePage } from '@/pages/AddIssue';

function App() {
  return (
    <RecoilRoot>
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Navigate to="/login" />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/issueList/*" element={<IssueListPage />} />
            <Route path="/redirect/oauth" element={<Callback />} />
            <Route path="/issueAdd" element={<AddIssuePage />} />
          </Routes>
        </BrowserRouter>
      </ThemeProvider>
    </RecoilRoot>
  );
}
export default App;
