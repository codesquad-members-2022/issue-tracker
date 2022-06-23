import { useState, useRef } from 'react';
import styled from 'styled-components';
import TextInput from '@/common/TextInput';
import Button, { ButtonBox } from '@/common/Button';
import { COLORS, GREYSCALE } from '@/constants';

type HasInputType = {
  id: boolean;
  password: boolean;
};

function Login() {
  const [hasInput, setHasInput] = useState<HasInputType>({
    id: false,
    password: false,
  });
  const [ErrorMessage, setErrorMessage] = useState('');

  const idRef = useRef<HTMLInputElement>(null);
  const passwordRef = useRef<HTMLInputElement>(null);
  const isValidUser = () => {
    // id, password 체크 함수
    // 백엔드 로그인 기능 개발전까지 false를 일관 반환 처리함
    return false;
  };

  const handleLoginButtonClick = () => {
    // 서버에 로그인 요청 후 응답 받았다고 가정

    if (!isValidUser()) {
      setErrorMessage('아이디와 비밀번호가 일치하지 않습니다.');

      if (idRef.current) {
        idRef.current.focus();
      }

      if (passwordRef.current) {
        passwordRef.current.value = '';
      }
    }
    // 로그인 성공
  };

  const handleInput = (event: React.ChangeEvent<HTMLInputElement>) => {
    switch (event.target.type) {
      case 'text':
        if (event.target.value.length) {
          setHasInput({ id: true, password: hasInput.password });
        } else {
          setHasInput({ id: false, password: hasInput.password });
        }
        break;
      case 'password':
        if (event.target.value.length) {
          setHasInput({ id: hasInput.id, password: true });
        } else {
          setHasInput({ id: hasInput.id, password: false });
        }
        break;
      default:
    }
  };

  return (
    <LoginBox>
      <GitHubLoginButton>GitHub 계정으로 로그인</GitHubLoginButton>
      <p>or</p>
      <LoginForm>
        <Inputs>
          <TextInput
            type="text"
            placeholder="아이디"
            minLength={6}
            maxLength={16}
            onChange={handleInput}
            refElement={idRef}
          />
          <TextInput
            type="password"
            placeholder="비밀번호"
            minLength={6}
            maxLength={12}
            onChange={handleInput}
            refElement={passwordRef}
          />
        </Inputs>
        <Button
          label="아이디로 로그인"
          disabled={!(hasInput.id && hasInput.password)}
          onClick={handleLoginButtonClick}
        />
      </LoginForm>
      {ErrorMessage && <ErrorMesageBox>{ErrorMessage}</ErrorMesageBox>}
    </LoginBox>
  );
}

const LoginBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column', 'center', 'center')}
  gap: 24px;

  p:first-child {
    ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
    color: ${GREYSCALE.PLACEHOLDER};
  }
`;

const LoginForm = styled.form`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column', 'center', 'center')}
  gap: 24px;
`;

const GitHubLoginButton = styled(ButtonBox)`
  background-color: ${GREYSCALE.TITLE_ACTION};
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

export default Login;
