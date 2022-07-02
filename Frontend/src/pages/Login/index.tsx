import axios, { AxiosResponse } from 'axios';
import React, { useCallback, useContext, useState } from 'react';
import { useCookies } from 'react-cookie';
import { useNavigate } from 'react-router-dom';

import Button from '@/components/Button';
import { LoginStatusContext } from '@/context/LoginStatusProvider';

import * as S from './style';

const LOGIN_REQUEST_URL = '/login';

export default function Login() {
  const navigate = useNavigate();
  const [cookies] = useCookies(['refreshToken']);
  const { loginStatus, setLoginStatus } = useContext(LoginStatusContext);

  const issueNumber = 1;
  const [loginInputValue, setLoginInputValue] = useState({
    id: '',
    password: '',
  });

  const isLoginButtonActive =
    loginInputValue.id !== '' && loginInputValue.password !== '';

  const handleChange = useCallback(
    (e: React.ChangeEvent<HTMLInputElement>, type: 'id' | 'password') => {
      switch (type) {
        case 'id':
          setLoginInputValue({ ...loginInputValue, id: e.currentTarget.value });
          break;
        case 'password':
          setLoginInputValue({
            ...loginInputValue,
            password: e.currentTarget.value,
          });
          break;
        default:
          break;
      }
    },
    [loginInputValue]
  );

  const handleClickLogin = async () => {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        await axios.get(LOGIN_REQUEST_URL, {
          headers: {
            Authorization: `Bearer ${token}`,
            refreshToken: `${cookies.refreshToken}`,
          },
        });
        setLoginStatus({ ...loginStatus, status: true });
        navigate('/main');
      } catch (error: any) {
        // access token 이 만료 전일 경우 로그인
        if (error.response.status === 301) {
          console.log(error);
          localStorage.setItem('token', error.response.data);
          setLoginStatus({ ...loginStatus, status: true });
          navigate('/main');
        } else {
          console.log(error);
          alert('로그인 실패');
        }
      }
      return;
    }
    const url: AxiosResponse = await axios.get(LOGIN_REQUEST_URL);
    window.location.href = url.data;
  };

  return (
    <S.LoginContainer>
      <S.Title>Issue Tracker</S.Title>
      <Button
        buttonStyle="largeLogin"
        hasIcon={false}
        contents="GitHub 계정으로 로그인"
        onClick={handleClickLogin}
      />
      <S.Content>or</S.Content>
      <S.LoginInput
        type="text"
        placeholder="아이디"
        onChange={(e) => handleChange(e, 'id')}
      />
      <S.LoginInput
        type="text"
        placeholder="비밀번호"
        onChange={(e) => handleChange(e, 'password')}
      />
      {/* TODO : issue 목록중 하나 선택 예시, 추후 이슈 목록 완성되면 그곳에 Link */}
      <S.LoginLink
        to={`/issue/${issueNumber}`}
        className={`disable-${!isLoginButtonActive}`}
      >
        <Button
          buttonStyle="large"
          hasIcon={false}
          contents="아이디로 로그인"
          disabled={!isLoginButtonActive}
        />
      </S.LoginLink>
      <Button buttonStyle="smallText" hasIcon={false} contents="회원가입" />
    </S.LoginContainer>
  );
}
