import React from 'react';
import { Input } from '@UI/Input';
import styles from './Issue.module.scss';
import IssuesNav from './IssuesNav';
import Issue from './Issue';

const Issues = () => {
  return (
    <>
      <div>필터 옵션</div>
      <button style={{ width: '300px', height: '100px', background: 'red' }}>
        이슈 등록하기
      </button>
      <div className={styles.wrapper}>
        <IssuesNav />
        <Issue />
        <Issue />
      </div>
    </>
  );
};

export default Issues;
