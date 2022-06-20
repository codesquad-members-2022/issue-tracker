import React from 'react';
import EmptyIssueItem from './EmptyIssueItem';
import IssueHeader from './IssueHeader';
import IssueItem from './IssueItem';

function IssueListPage() {
  return (
    <div>
      <IssueHeader />
      <div>
        <IssueItem />
        <IssueItem isLast />
      </div>
    </div>
  );
}

export default IssueListPage;
