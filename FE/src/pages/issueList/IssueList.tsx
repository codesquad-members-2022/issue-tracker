import React from 'react';
import { Header } from '@components/header';
import Issues from '@components/issue/Issues';
import IssuesFilter from '@components/issueFilter/IssuesFilter';

const IssueList = () => {
  return (
    <>
      <Header />
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <IssuesFilter />
        <Issues />
      </div>
    </>
  );
};

export default IssueList;
