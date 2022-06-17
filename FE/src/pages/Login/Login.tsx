import React from 'react';

import { GitHubBtn } from './Login.styled';
import * as S from './Login.styled';

function Login(): JSX.Element {
  return (
    <S.Container>
      <S.Wrapper>
        <S.Logo>
          <S.LogoImg alt="logo" src="./LogotypeLarge.svg" />
        </S.Logo>
        <GitHubBtn to="/" size="lg">
          GitHub 계정으로 로그인
        </GitHubBtn>
        <S.OR
          fontWeight="bold"
          fontSize="sm"
          lineheight="short"
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
          lineheight="shorter"
          color="body"
        >
          회원가입
        </S.MemberBtn>
      </S.Wrapper>
    </S.Container>
  );
}
export default Login;
