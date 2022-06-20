import { useState, useRef } from 'react';
import styled from 'styled-components';
import Button, { ButtonBox } from '@/common/Button';
import { COLORS, GREYSCALE } from '@/constants';
import TextInput from '@/common/TextInput';

function LoginPage() {
  const [id, setId] = useState('');
  const [password, setPassword] = useState('');
  const [ErrorMessage, setErrorMessage] = useState('');
  const idRef = useRef<HTMLInputElement>(null);

  const isValidUser = () => {
    // id, password 체크 함수
    // 백엔드 로그인 기능 개발전까지 false를 일관 반환 처리함
    return false;
  };

  const handleUserInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { type, value } = event.target;
    switch (type) {
      case 'text':
        setId(value);
        break;
      case 'password':
        setPassword(value);
        break;
      default:
    }
  };

  const handleLoginButtonClick = (
    event: React.MouseEvent<HTMLButtonElement>,
  ) => {
    if (!isValidUser()) {
      event.preventDefault();
      setPassword('');
      setErrorMessage('아이디와 비밀번호가 일치하지 않습니다.');

      if (!idRef.current) {
        return;
      }
      idRef.current.focus();
    }
    // 로그인 성공
  };

  return (
    <LoginPageBox>
      <Title>Issue Tracker</Title>
      <GitHubLoginButton>GitHub 계정으로 로그인</GitHubLoginButton>
      <Or>or</Or>
      <LoginForm>
        <Inputs>
          <TextInput
            type="text"
            placeholder="아이디"
            value={id}
            minLength={6}
            maxLength={16}
            onChange={handleUserInput}
            ref={idRef}
          />
          <TextInput
            type="password"
            placeholder="비밀번호"
            value={password}
            minLength={6}
            maxLength={12}
            onChange={handleUserInput}
          />
        </Inputs>
        <Button
          label="아이디로 로그인"
          disabled={!(id && password)}
          onClick={handleLoginButtonClick}
        />
      </LoginForm>
      {ErrorMessage && <ErrorMesageBox>{ErrorMessage}</ErrorMesageBox>}
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

const LoginForm = styled.form`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column', 'center', 'center')}
  gap: 24px;
`;

const GitHubLoginButton = styled(ButtonBox)`
  background-color: ${GREYSCALE.TITLE_ACTION};
`;

const Or = styled.div`
  margin: 24px 0;
  color: ${GREYSCALE.PLACEHOLDER};
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
`;

const Inputs = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column', 'center', 'center')}
  gap: 16px;
`;

const ErrorMesageBox = styled.p`
  margin-top: 8px;
  color: ${COLORS.DARK_RED};
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_X_SMALL}
`;

export default LoginPage;
