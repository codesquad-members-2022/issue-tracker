import React from 'react';
import { Input } from '@UI/Input';
import styles from './Issue.module.scss';
import IssuesNav from './IssuesNav';
import Issue from './Issue';

const Issues = () => {
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
