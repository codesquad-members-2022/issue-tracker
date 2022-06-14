import React, { useRef } from 'react';
import styles from './AuthForm.module.scss';
import Input from '@UI/Input';

const AuthForm = () => {
  const emailInputRef = useRef<HTMLInputElement>(null);
  const passwordInputRef = useRef<HTMLInputElement>(null);

  return (
    <section className={styles.section}>
      <h1 className={styles.title}>Issue Tracker</h1>
      <div className={styles.wrapper}>
        <button className={styles.github_button}>GitHub 계정으로 로그인</button>
        <div className={styles.divider}>or</div>
        <form className={styles.form}>
          <Input
            ref={emailInputRef}
            label="email"
            info={{
              id: 'email',
              type: 'text',
              placeHolder: '아이디',
            }}
          />
          <Input
            ref={passwordInputRef}
            label="password"
            info={{
              id: 'password',
              type: 'text',
              placeHolder: '비밀번호',
            }}
          />
          <button className={styles.login_button}>아이디로 로그인</button>
        </form>
      </div>
      <button className={styles.signUp_button}>회원 가입</button>
    </section>
  );
};

export default AuthForm;
