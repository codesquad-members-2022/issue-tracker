import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import GlobalStyle from './common/GlobalStyle';
import HeaderPage from './pages/header/HeaderPage';
import IssuesPage from './pages/issues/IssuesPage';
import LoginPage from './pages/login/LoginPage';

export default function App() {
  return (
    <>
      <GlobalStyle />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/" element={<HeaderPage />}>
            <Route path="/issues" element={<IssuesPage />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}
