import React from 'react';

import * as S from './Login.styled';

function Login(): JSX.Element {
  return (
    <S.Container>
      <S.Wrapper>
        <S.Logo>
          <S.LogoImg />
        </S.Logo>
        <S.GitHubBtn to="/" size="sm">
          GitHub
        </S.GitHubBtn>
        <S.GitHubBtn to="/" size="md">
          GitHub 계정으로
        </S.GitHubBtn>
        <S.GitHubBtn to="/" size="lg">
          GitHub 계정으로 로그인
        </S.GitHubBtn>
      </S.Wrapper>
    </S.Container>
  );
}
export default Login;
