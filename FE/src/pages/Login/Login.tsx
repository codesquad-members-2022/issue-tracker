// import { useEffect } from 'react';
import axios from 'axios';
import { GitHubBtn } from './Login.styled';
import * as S from './Login.styled';

export function Login() {
  const URI = '/api/login';

  const getToken = async () => {
    try {
      const response = await axios.get(URI);
      // const data = await response;

      // eslint-disable-next-line no-console
      console.log('ssss', response);
    } catch (error) {
      // eslint-disable-next-line no-console
      console.log('???????error???????');
      // eslint-disable-next-line no-console
      console.log(error);
    }
  };

  return (
    <S.Container>
      <S.Wrapper>
        <S.Logo>
          <S.LogoImg alt="logo" src="./imgs/LogotypeLarge.svg" />
        </S.Logo>
        <GitHubBtn onClick={getToken} size="lg">
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
