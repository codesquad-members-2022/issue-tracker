import React from 'react';
import styles from './IssuesFilter.module.scss';
import { useNavigate } from 'react-router-dom';

const IssuesFilter = () => {
  const navigate = useNavigate();
  const onClickHandler = () => {
    navigate('/addIssue');
  };
  return (
    <nav className={styles.filterNav}>
      <div className={styles.filterWrapper}></div>
      <div className={styles.filterButtonsWrapper}>
        <button className={styles.addIssueButton} onClick={onClickHandler}>
          이슈작성
        </button>
      </div>
    </nav>
  );
};

export default IssuesFilter;
