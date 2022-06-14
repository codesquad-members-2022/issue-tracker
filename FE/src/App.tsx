import React from 'react';
import '@styles/reset.css';
import { Header } from '@components/header';
import { AddIssue } from './pages/addIssue';

const App = () => {
  return (
    <>
      <Header />
      <AddIssue />
    </>
  );
};

export default App;
