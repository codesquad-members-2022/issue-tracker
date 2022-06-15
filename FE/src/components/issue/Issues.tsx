import React from 'react';
import { Input } from '@UI/Input';
import styles from './Issue.module.scss';
import IssuesNav from './IssuesNav';
import Issue from './Issue';

const Issues = () => {
  return (
    <>
      <button>이슈 등록하기</button>
      <div className={styles.wrapper}>
        <IssuesNav />
        <Issue />
        <Issue />
      </div>
    </>
  );
};

export default Issues;
