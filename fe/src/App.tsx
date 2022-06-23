import LoginPage from "components/common/loginPage/LoginPage";
import React from "react";
import { Routes, Route } from "react-router-dom";

import IssueListPage from "./components/issueListPage/IssueListPage";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/issues" element={<IssueListPage />} />
        <Route path="/addIssue" element={<div>이슈추가</div>} />
        <Route path="/label" element={<div>레이블</div>} />
        <Route path="/milestone" element={<div>마일스톤</div>} />
      </Routes>
    </div>
  );
}

export default App;
