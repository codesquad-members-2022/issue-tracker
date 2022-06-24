import { ThemeProvider } from 'styled-components';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from '@/login/LoginPage';
import IssueListPage from '@/issueList/IssueListPage';
import LabelListPage from './labelList/LabelListPage';
import MilestoneListPage from './milestoneList/MilestoneListPage';
import AddIssuePage from './addIssue/AddIssuePage';
import IssueDetailPage from './issueDetail/IssueDetailPage';
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
            <Route path="labelList" element={<LabelListPage />} />
            <Route path="milestoneList" element={<MilestoneListPage />} />
            <Route path="addIssue" element={<AddIssuePage />} />
            <Route path="issueDetail" element={<IssueDetailPage />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </ThemeProvider>
  );
}
