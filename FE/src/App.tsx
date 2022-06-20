import React from 'react';
import { ThemeProvider } from 'styled-components';
import GlobalStyles from './GlobalStyles';
import { theme } from './theme';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from '@/login/LoginPage';
import IssueListPage from '@/issueList/IssueListPage';

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<LoginPage />}></Route>
          <Route path="issueList" element={<IssueListPage />}></Route>
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}
