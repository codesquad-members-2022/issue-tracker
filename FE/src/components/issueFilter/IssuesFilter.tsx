import React from 'react';
import styles from './IssuesFilter.module.scss';

const IssuesFilter = () => {
  return (
    <nav className={styles.filterNav}>
      <div className={styles.filterWrapper}></div>
      <div className={styles.filterButtonsWrapper}>
        <button className={styles.addIssueButton}>이슈작성</button>
      </div>
    </nav>
  );
};

export default IssuesFilter;
