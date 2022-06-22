import React from 'react';
import styles from './IssuesNav.module.scss';
import { Input } from '../../common/Input';

type IssuesNavPropType = {
  allCheckboxHandler: (e: React.ChangeEvent<HTMLInputElement>) => void;
  checkedIssues: string[];
  isChecked: boolean;
};

const IssuesNav = ({
  allCheckboxHandler,
  isChecked,
  checkedIssues,
}: IssuesNavPropType) => {
  return (
    <nav className={styles.nav}>
      <div className={styles.checkBoxWrapper}>
        <Input
          label="issueAllSelect"
          info={{
            id: 'issueAllSelect',
            type: 'checkbox',
            value: 'allSelect',
            onChange: allCheckboxHandler,
            checked: isChecked,
          }}
        />
      </div>

      <div className={styles.nav__contentWrapper}>
        {!isChecked && (
          <>
            <div>
              <span>열린 이슈</span>
              <span>닫힌 이슈</span>
            </div>
            <div className={styles.right}>
              <span>담당자</span>
              <span>담당자</span>
              <span>담당자</span>
              <span>담당자</span>
            </div>
          </>
        )}
        {isChecked && (
          <>
            <span>{checkedIssues.length}개의 이슈를 선택했습니다.</span>
            <button style={{ marginRight: '3rem' }}>상태 수정하기</button>
          </>
        )}
      </div>
    </nav>
  );
};

export default IssuesNav;
