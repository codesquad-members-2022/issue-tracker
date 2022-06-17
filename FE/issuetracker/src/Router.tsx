import IssueList from 'components/IssueList/index';
import Login from 'components/Login/Index';
import { Route, Routes } from 'react-router-dom';
import Issue from 'components/Issue/index';
import LabelPage from 'components/LabelPage';

function Router() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/issueList" element={<IssueList />} />
      <Route path="/newIssue" />
      <Route path="/issue" element={<Issue />} />
      <Route path="/label" element={<LabelPage />} />
      <Route path="/milestone" />
    </Routes>
  );
}

export default Router;
