import React, { useRef } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styles from './AuthForm.module.scss';
import { Input } from '@UI/Input';
import { authActions } from '../../store/authStore';
import { RootStateType } from 'src/store/store';
const url = `https://github.com/login/oauth/authorize?client_id=a1bc16e4f3e6c7202a57&redirect_uri=http://localhost:8081/callback`;
const AuthForm = () => {
  const dispatch = useDispatch();
  const githubLoginHandler = () => {};
  const idSubmitHandler = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // 아이디로 로그인
    dispatch(authActions.login('123'));
  };

  return (
    <section className={styles.section}>
      <h1 className={styles.title}>Issue Tracker</h1>
      <div className={styles.wrapper}>
        <a className={styles.github_button} href={url}>
          GitHub 계정으로 로그인
        </a>
        <div className={styles.divider}>or</div>
        <form className={styles.form} onSubmit={idSubmitHandler}>
          <Input
            label="email"
            info={{
              id: 'email',
              type: 'email',
              placeholder: '아이디',
            }}
          />
          <Input
            label="password"
            info={{
              id: 'password',
              type: 'password',
              placeholder: '비밀번호',
            }}
          />
          <button type="submit" className={styles.login_button}>
            아이디로 로그인
          </button>
        </form>
      </div>
      <button className={styles.signUp_button}>회원 가입</button>
    </section>
  );
};

export default AuthForm;
