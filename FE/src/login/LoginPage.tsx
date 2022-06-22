import styled from 'styled-components';
import Login from './Login';

function LoginPage() {
  return (
    <LoginPageBox>
      <Title>Issue Tracker</Title>
      <Login />
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

export default LoginPage;
