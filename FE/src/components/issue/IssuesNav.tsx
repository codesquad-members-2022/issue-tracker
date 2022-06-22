import React from 'react';
import styles from './IssuesNav.module.scss';
import { Input } from '@UI/Input';

const IssuesNav = ({ allCheckboxHandler }) => {
  return (
    <nav className={styles.navWrapper}>
      <div className={styles.leftWrapper}>
        <div className={styles.checkBoxWrapper}>
          <Input
            label="issueAllSelect"
            info={{
              id: 'issueAllSelect',
              type: 'checkbox',
              value: 'allSelect',
              onChange: allCheckboxHandler,
            }}
          />
        </div>
        <div className={styles.nav__contentWrapper}>
          <span>열린 이슈</span>
          <span>닫힌 이슈</span>
        </div>
      </div>

      <div className={styles.rightWrapper}>
        <details>
          <summary>담당자</summary>
          <div className={styles.test}>123123123</div>
        </details>
        <details>
          <summary>레이블</summary>
          <div className={styles.test}>123123123</div>
        </details>
        <details>
          <summary>마일스톤</summary>
          <div className={styles.test}>123123123</div>
        </details>
        ㄴ
        <details>
          <summary>작성자</summary>
          <div className={styles.test}>123123123</div>
        </details>
      </div>
    </nav>
  );
};

export default IssuesNav;
