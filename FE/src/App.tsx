import React from 'react';
import AuthForm from '@components/Auth/AuthForm';
import { Header } from '@components/header';
import { AddIssue } from './pages/addIssue';
import IssueList from '@pages/issueList/IssueList';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/">
          <Route index element={<AuthForm />} />
          <Route path="issues" element={<IssueList />} />
          <Route path="addIssue" element={<AddIssue />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;
