import React from 'react';
import styles from './addIssueForm.module.scss';
import { TextForm } from './textForm';

export const AddIssueForm = (): JSX.Element => {
  return (
    <form>
      <div className={styles.Box}>
        <div className={styles.userImg}>
          <img src={'assets/img/UserImage.png'} />
        </div>
        <div className={styles.inputBox}>
          <TextForm />
        </div>
        <div></div>
      </div>
      <div>
        <button>작성 취소</button>
        <button>완료</button>
      </div>
    </form>
  );
};
