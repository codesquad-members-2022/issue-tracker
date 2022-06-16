import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import styles from './AuthForm.module.scss';
import { Input } from '@UI/Input';
import { authActions } from '../../store/authStore';
import GithubLoginBtn from './GithubLoginBtn';
import { useLocation } from 'react-router-dom';

const AuthForm = () => {
  const { search } = useLocation();
  const dispatch = useDispatch();
  const idSubmitHandler = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    // 아이디로 로그인
    dispatch(authActions.login());
  };

  return (
    <>
      <section className={styles.section}>
        <h1 className={styles.title}>Issue Tracker</h1>
        <div className={styles.wrapper}>
          <GithubLoginBtn />
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
    </>
  );
};

export default AuthForm;
