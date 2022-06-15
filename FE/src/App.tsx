import React from 'react';
import AuthForm from '@components/Auth/AuthForm';
import Issues from '@components/issue/Issues';
import { Header } from '@components/header';
import { AddIssue } from './pages/addIssue';
import IssueList from './pages/issueList.tsx/IssueList';

const App = () => {
  return <IssueList />;
};

export default App;
