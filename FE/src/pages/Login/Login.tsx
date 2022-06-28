import { useEffect } from 'react';
import { GitHubBtn } from './Login.styled';
import * as S from './Login.styled';

const URL = `http://louie-03.com/login`;

export function Login(): JSX.Element {
  // const URI = 'http://louie-03.com/login';

  useEffect(() => {
    const getToken = async () => {
      try {
        const response = await fetch(URL);
        const data = await response.json();
        // eslint-disable-next-line no-console
        console.log('ssss', data.access_token);
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log('???????error???????');
        // eslint-disable-next-line no-console
        console.log(error);
      }
    };
    getToken();
  });
  return (
    <S.Container>
      <S.Wrapper>
        <S.Logo>
          <S.LogoImg alt="logo" src="./imgs/LogotypeLarge.svg" />
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
