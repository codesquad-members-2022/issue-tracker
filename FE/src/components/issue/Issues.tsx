import React from 'react';
import { Input } from '@UI/Input';
import styles from './Issue.module.scss';
import IssuesNav from './IssuesNav';
import Issue from './Issue';
import { QueryClient, useQuery } from 'react-query';

const fetchIssues = async () => {
  const response = await fetch(`http://localhost:3030/issues`);
  if (!response.ok) {
    throw new Error('response not ok');
  }

  return response.json();
};

const queryClient = new QueryClient();

const Issues = () => {
  const { data } = useQuery('issues', fetchIssues);
  console.log(data);

  // if (status === 'loading') {
  //   console.log('loading');
  // }
  return (
    <>
      <div className={styles.wrapper}>
        <IssuesNav />
        <Issue />
        <Issue />
      </div>
    </>
  );
};

export default Issues;
