import { Route, Routes } from 'react-router-dom';

function Router() {
  return (
    <Routes>
      <Route path="/login"></Route>
      <Route path="/issueList"></Route>
      <Route path="/newIssue"></Route>
      <Route path="/issue"></Route>
      <Route path="/label"></Route>
      <Route path="/milestone"></Route>
    </Routes>
  );
}

export default Router;
