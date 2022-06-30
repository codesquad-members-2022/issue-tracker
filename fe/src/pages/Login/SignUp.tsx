import { Dispatch, SetStateAction } from 'react';
import { useQueryClient } from 'react-query';

import styled from 'styled-components';
import Logo from 'components/Atoms/Logo';
import SignUpForm from 'components/Organisms/SignUpForm';

import { AuthTypes } from 'helpers/utils/fetchData';

const StyledSignUp = styled.div`
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  form {
    padding: 0;

    input {
      padding: 0 24px;
      height: 100%;
      border-radius: 16px;
    }
  }

  a {
    margin-bottom: 60px;
  }
`;

const SignUp = ({ setIsOAuth }: { setIsOAuth: Dispatch<SetStateAction<boolean>> }) => {
  const queryClient = useQueryClient();
  const data = queryClient.getQueryData<AuthTypes>('auth');

  if (!data) return <div />;

  return (
    <StyledSignUp>
      <Logo logoSize="Large" />
      <SignUpForm authData={data.authMemberResponse} setIsOAuth={setIsOAuth} />
    </StyledSignUp>
  );
};

export default SignUp;
