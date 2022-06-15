import React from 'react';
import styles from './IssuesNav.module.scss';
import { Input } from '@UI/Input';

const Issue = () => {
  return (
    <div className={styles.issueWrapper}>
      <div className={styles.leftWrapper}>
        <div className={styles.checkBoxWrapper}>
          <Input
            label="issueAllSelect"
            info={{
              id: 'issueAllSelect',
              type: 'checkbox',
              value: 'allSelect',
            }}
          />
        </div>
        <div className={styles.issue__contentWrapper}>
          <div>
            <span>열린 이슈</span>
            <span>닫힌 이슈</span>
          </div>
          <div>
            <span>열린 이슈</span>
            <span>닫힌 이슈</span>
          </div>
        </div>
      </div>

      <div className={styles.rightWrapper}>
        <div>무언가</div>
      </div>
    </div>
  );
};

export default Issue;
