import React from 'react';

import { GitHubBtn } from './Login.styled';
import * as S from './Login.styled';

const URL = `https://github.com/login/oauth/authorize?client_id=${process.env.REACT_APP_GITHUB_CLIENT_ID}&scope=read:user&redirect_uri=${process.env.REACT_APP_GITHUB_REDIRECT_URI}`;

function Login(): JSX.Element {
  return (
    <S.Container>
      <S.Wrapper>
        <S.Logo>
          <S.LogoImg alt="logo" src="./LogotypeLarge.svg" />
        </S.Logo>
        <GitHubBtn href={URL} size="lg">
          GitHub 계정으로 로그인
        </GitHubBtn>
        <S.OR
          fontWeight="bold"
          fontSize="sm"
          lineHeight="short"
          color="placeholder"
        >
          OR
        </S.OR>
        <S.IdInput placeholder="아이디" type="text" />
        <S.PasswordInput placeholder="비밀번호" type="password" />
        <S.IdBtn to="/" size="lg">
          아이디로 로그인
        </S.IdBtn>
        <S.MemberBtn
          to="/"
          fontWeight="bold"
          fontSize="xs"
          lineHeight="shorter"
          color="body"
        >
          회원가입
        </S.MemberBtn>
      </S.Wrapper>
    </S.Container>
  );
}
export default Login;
