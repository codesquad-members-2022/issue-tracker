import { ThemeProvider } from 'styled-components';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from '@/pages/LoginPage';
import IssueListPage from '@/pages/IssueListPage';
import { lazy, Suspense } from 'react';
import GlobalStyles from './GlobalStyles';
import theme from './theme';
import Layout from './common/components/Layout';
import { PATH } from './common/constants';

const pages = Object.values(PATH).sort();

const getFileName = (page: string) => {
  const fileName = `${page[0].toUpperCase()}${page.slice(1)}Page`;

  return fileName;
};

const getPage = (page: string) => {
  const fileName = getFileName(page);
  const Page = lazy(() => import(`./pages/${fileName}`));

  return (
    <Suspense fallback={<div>Loading...</div>}>
      <Page />
    </Suspense>
  );
};

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyles />
      <BrowserRouter>
        <Routes>
          <Route path="login" element={<LoginPage />} />
          <Route path="/" element={<Layout />}>
            <Route path="issueList" element={<IssueListPage />} />
            {pages.map((page, idx) => {
              return <Route key={idx} path={page} element={getPage(page)} />;
            })}
          </Route>
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}
