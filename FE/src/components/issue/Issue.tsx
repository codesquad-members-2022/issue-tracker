import React from 'react';
import styles from './Issue.module.scss';
import { Input } from '@UI/Input';

const Issue = () => {
  return (
    <div className={styles.issueWrapper}>
      <div className={styles.leftWrapper}>
        <div className={styles.checkBoxWrapper}>
          <Input
            label="issueSelect"
            info={{
              id: 'issueSelect',
              type: 'checkbox',
              value: 'issueSelect',
            }}
          />
        </div>
        <div className={styles.issue__contentWrapper}>
          <div>
            <span>이슈 제목</span>
            <span>badge</span>
          </div>
          <div>
            <span>#이슈번호</span>
            <span>작성자 및 타임스탬프 정보</span>
            <span>마일스톤</span>
          </div>
        </div>
      </div>

      <div className={styles.rightWrapper}>
        <div>무언가</div>
        <div>무언가</div>
        <div>무언가</div>
        <div>무언가</div>
      </div>
    </div>
  );
};

export default Issue;
