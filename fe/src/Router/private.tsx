import { Route, Routes } from 'react-router-dom';
import { Home, Issues, IssueDetail, AddIssue, MilestoneList, LabelList, NotFound } from 'pages';
import { HomeTypes } from 'Router';

const PrivateRouter = ({ isOAuth, setIsOAuth }: HomeTypes) => {
  return (
    <Routes>
      <Route path="/" element={<Home isOAuth={isOAuth} setIsOAuth={setIsOAuth} />}>
        <Route index element={<Issues />} />
        <Route path="/issues" element={<Issues />} />
        <Route path="/issues/new" element={<AddIssue />} />
        <Route path="/issues/:issueId" element={<IssueDetail />} />
        <Route path="/labels" element={<LabelList />} />
        <Route path="/milestones" element={<MilestoneList />} />
      </Route>
      <Route path="*" element={<NotFound />} />
    </Routes>
  );
};

export default PrivateRouter;
