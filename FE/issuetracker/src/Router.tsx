import IssueList from 'components/IssueList/index';
import Login from 'components/Login/Index';
import { Route, Routes } from 'react-router-dom';
import Issue from 'components/Issue/index';
import NewIssue from 'components/newIssue';
import LabelPage from 'components/LabelPage';
import MileStone from 'components/milestone/Index';

function Router() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route path="/issueList" element={<IssueList />} />
      <Route path="/newIssue" element={<NewIssue />} />
      <Route path="/issue" element={<Issue />} />
      <Route path="/label" element={<LabelPage />} />
      <Route path="/milestone" element={<MileStone />} />
    </Routes>
  );
}

export default Router;
