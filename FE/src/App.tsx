import React from 'react';
import AuthForm from '@components/Auth/AuthForm';
import '@styles/reset.css';
import { Header } from '@components/header';
import { AddIssue } from './pages/addIssue';

const App = () => {
  return (
    <AuthForm />
    // <>
    //   <Header />
    //   <AddIssue />
    // </>
  );
};

export default App;
