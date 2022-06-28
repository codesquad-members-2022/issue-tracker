import React, { useRef } from 'react';
import styles from './IssuesFilter.module.scss';
import { useNavigate } from 'react-router-dom';
import { InputWithRef } from '../../common/Input';

const IssuesFilter = () => {
  const navigate = useNavigate();
  const onClickHandler = () => {
    navigate('/addIssue');
  };
  const searchRef = useRef<HTMLInputElement>(null);
  return (
    <nav className={styles.filterNav}>
      <div className={styles.filterWrapper}>
        <div className={styles.filter}>필터</div>
        <InputWithRef
          label="search"
          ref={searchRef}
          info={{
            id: 'id',
            name: 'idInput',
            type: 'text',
            placeholder: 'is:issue is:open',
            className: styles.search,
          }}
        ></InputWithRef>
      </div>
      <div className={styles.filterButtonsWrapper}>
        <div className={styles.buttons}>
          <button>레이블 (0) </button>
          <button>마일스톤 (0) </button>
        </div>
        <button className={styles.addIssueButton} onClick={onClickHandler}>
          이슈작성
        </button>
      </div>
    </nav>
  );
};

export default IssuesFilter;
