import React from 'react';
import styled from 'styled-components';
import Button, { ButtonBox } from '@/common/Button';
import { GREYSCALE } from '@/constants';
import TextInput from '@/common/TextInput';

function LoginPage() {
  return (
    <LoginPageBox>
      <Title>Issue Tracker</Title>
      <LoginForm>
        <LoginButton>GitHub 계정으로 로그인</LoginButton>
        <Or>or</Or>
        <Inputs>
          <TextInput type="text" placeholder="아이디" />
          <TextInput type="password" placeholder="비밀번호" />
        </Inputs>
        <Button label="아이디로 로그인" isDisabled />
      </LoginForm>
    </LoginPageBox>
  );
}

const LoginPageBox = styled.div`
  height: 100vh;
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column', 'center', 'center')}
`;

const Title = styled.h1`
  ${({ theme }) => theme.TYPOGRAPHY.LOGOTYPE_LARGE}
  margin-bottom: 61px;
`;

const LoginForm = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column', 'center', 'center')}
  gap: 24px;
`;

const LoginButton = styled(ButtonBox)`
  background-color: ${GREYSCALE.TITLE_ACTION};
`;

const Or = styled.div`
  color: ${GREYSCALE.PLACEHOLDER};
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
`;

const Inputs = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column', 'center', 'center')}
  gap: 16px;
`;

export default LoginPage;
