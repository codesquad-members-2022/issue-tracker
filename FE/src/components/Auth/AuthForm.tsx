import React from 'react';
import { ParsedQs } from 'qs';
import styles from './AuthForm.module.scss';
import { Input } from '../../UI/Input';
import GithubLoginBtn from './GithubLoginBtn';
import useInput from '../../hooks/useInput';

export type parsedQueryType =
  | string
  | ParsedQs
  | string[]
  | ParsedQs[]
  | undefined
  | null;

const AuthForm = () => {
  const {
    value: enteredID,
    isValid: idIsValid,
    valueChangeHandler: IDChangedHandler,
    reset: resetID,
  } = useInput((value) => value.length > 5);

  const {
    value: enteredPassword,
    isValid: passWordIsValid,
    valueChangeHandler: passWordChangedHandler,
    reset: resetPassword,
  } = useInput((value) => value.length > 5);

  const formIsValid = idIsValid && passWordIsValid;

  const submitHandler = (e: React.FormEvent) => {
    e.preventDefault();

    resetID();
    resetPassword();
  };

  return (
    <section className={styles.section}>
      <h1 className={styles.title}>Issue Tracker</h1>
      <div className={styles.wrapper}>
        <GithubLoginBtn />
        <div className={styles.divider}>or</div>
        <form className={styles.form} onSubmit={submitHandler}>
          <Input
            label="id"
            info={{
              id: 'id',
              type: 'text',
              placeholder: '아이디',
              value: enteredID,
              onChange: IDChangedHandler,
              maxLength: 12,
            }}
          />
          <Input
            label="password"
            info={{
              id: 'password',
              type: 'password',
              placeholder: '비밀번호',
              value: enteredPassword,
              onChange: passWordChangedHandler,
              maxLength: 12,
            }}
          />
          <button
            type="submit"
            className={styles.login_button}
            disabled={!formIsValid}
          >
            아이디로 로그인
          </button>
        </form>
      </div>
      <button className={styles.signUp_button}>회원 가입</button>
    </section>
  );
};

export default AuthForm;
