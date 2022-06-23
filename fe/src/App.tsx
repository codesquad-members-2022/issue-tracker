import React from "react";
import { Routes, Route } from "react-router-dom";

import IssueListPage from "./components/issueListPage/IssueListPage";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<div>로그인</div>} />
        <Route path="/issues" element={<IssueListPage />} />
        <Route path="/label" element={<div>레이블</div>} />
        <Route path="/milestone" element={<div>마일스톤</div>} />
      </Routes>
    </div>
  );
}

export default App;
