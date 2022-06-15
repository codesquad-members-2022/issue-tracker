import React from 'react';

import * as S from './Login.styled';

function Login(): JSX.Element {
  return (
    <S.Container>
      <S.Wrapper>
        <S.Logo>
          <S.LogoImg />
        </S.Logo>
        {/* <GitHubBtn to="/" color="primary">
          GitHub 계정으로 로그인
        </GitHubBtn> */}
      </S.Wrapper>
    </S.Container>
  );
}
export default Login;
