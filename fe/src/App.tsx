import React, { useState } from "react";
import { Routes, Route } from "react-router-dom";

import LoginPage from "components/common/loginPage/LoginPage";
import Callback from "components/common/Callback";
// import IssueListPage from "./components/issueListPage/IssueListPage";
// import Input from "components/common/Input";
// import TextArea from "components/common/TextArea";
import IssueListPage from "components/issueListPage/IssueListPage";
import { RecoilRoot, atom, selector, useRecoilState, useRecoilValue } from "recoil";

function App() {
  const [isLogin, setIsLogin] = useState(false);

  return (
    <div>
      <RecoilRoot>
      <Routes>
        <Route path="/" element={isLogin ? <IssueListPage /> : <LoginPage />} />
        <Route path="/oauth/callback" element={<Callback setIsLogin={setIsLogin} />} />
        <Route path="/issues" element={<IssueListPage />} />
        <Route path="/addIssue" element={<div>이슈추가</div>} />
        <Route path="/label" element={<div>레이블</div>} />
        <Route path="/milestone" element={<div>마일스톤</div>} />
      </Routes>
      {/* <div> */}
        {/* <IssueListPage /> */}
        {/* <TextArea type="filled" /> */}
      {/* </div> */}
    </RecoilRoot>
    </div>
  );
}

export default App;
