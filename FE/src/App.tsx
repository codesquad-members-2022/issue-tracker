import React from 'react';
import AuthForm from '@components/Auth/AuthForm';
import Issues from '@components/issue/Issues';
import '@styles/reset.css';
import { Header } from '@components/header';
import { AddIssue } from './pages/addIssue';

const App = () => {
  return (
    // <AuthForm />
    // <>
    //   <Header />
    //   <AddIssue />
    // </>
    <Issues />
  );
};

export default App;
