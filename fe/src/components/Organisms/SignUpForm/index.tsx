/* eslint-disable camelcase */
import React, { Dispatch, SetStateAction, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useMutation } from 'react-query';

import axios from 'axios';

import styled from 'styled-components';
import Button from 'components/Atoms/Button';
import Input from 'components/Atoms/Input';

import { AuthTypes } from 'helpers/utils/fetchData';

const StyledSignUpForm = styled.div`
  form + form {
    margin-top: 10px;
  }

  button {
    margin-top: 50px;
  }

  span {
    display: inline-block;
    margin: 15px 10px;
  }

  form:disabled {
    background: ${({ theme }) => theme.colors.line};
  }
`;

interface SignUpFormTypes {
  id: number;
  login: string;
  name: string | null;
  email: string | null;
  avatar_url: string;
}

interface UserInfoTypes {
  oauth_id: number | null;
  login_id: string;
  login_password: string | null;
  name: string | null;
  email: string | null;
  profile_image_url: string;
}

type UserInfoKeys = 'login_id' | 'login_password' | 'name' | 'email' | 'profile_image_url' | 'oauth_id';

type ResponsesUserInfoTypes = Pick<AuthTypes, 'memberResponse' | 'jwtResponse'>;

const SignUpForm = ({
  authData,
  setIsOAuth,
}: {
  authData: SignUpFormTypes;
  setIsOAuth: Dispatch<SetStateAction<boolean>>;
}) => {
  // eslint-disable-next-line camelcase
  const { id, login, name, email, avatar_url } = authData;

  const initUserInfo: UserInfoTypes = {
    login_id: login,
    login_password: null,
    name,
    email,
    profile_image_url: avatar_url,
    oauth_id: id,
  };

  // 값이 하나라도 비어있으면 회원가입 버튼 비활성화하는 기능 추가하기
  const [userInfo, setUserInfo] = useState(initUserInfo);

  const isFilled = () => {
    let fill = true;

    Object.keys(userInfo).forEach((key) => {
      const k = key as UserInfoKeys;
      if (!userInfo[k]) fill = false;
    });

    return fill;
  };

  const formData = [
    { key: 0, inputType: 'text', maxLength: 12, placeholder: '아이디', userData: login, infoKey: 'login_id' },
    {
      key: 1,
      inputType: 'password',
      maxLength: 12,
      placeholder: '비밀번호',
      userData: null,
      infoKey: 'login_password',
    },
    { key: 2, inputType: 'email', maxLength: 12, placeholder: '이메일', userData: email, infoKey: 'email' },
    { key: 3, inputType: 'text', maxLength: 12, placeholder: '닉네임', userData: name, infoKey: 'name' },
  ];

  const addUser = async (newUser: UserInfoTypes): Promise<ResponsesUserInfoTypes> => {
    const { data: addLabelData } = await axios.post<ResponsesUserInfoTypes>(
      `${process.env.REACT_APP_SERVER_URL}/api/members`,
      newUser,
    );
    return addLabelData;
  };

  const navigate = useNavigate();

  const { mutate } = useMutation(addUser, {
    onSuccess: async (data) => {
      const { jwtResponse, memberResponse } = data;

      axios.defaults.headers.common.Authorization = `Bearer ${jwtResponse.accessToken}`;
      axios.defaults.withCredentials = true;

      localStorage.setItem('refresh_token', jwtResponse.refreshToken);
      localStorage.setItem('userInfo', JSON.stringify(memberResponse));

      await setIsOAuth(true);
      navigate('/issues');
    },
  });

  const handleClickSignUpButton = () => {
    mutate(userInfo);
  };

  return (
    <StyledSignUpForm>
      {formData.map(({ key, inputType, maxLength, placeholder, userData, infoKey }) => (
        <div key={key}>
          <span>{placeholder}</span>
          <Input
            inputMaxLength={maxLength}
            inputPlaceholder={placeholder}
            inputSize="LARGE"
            inputType={inputType}
            inputValue={userData!}
            onClick={() => console.log('클릭')}
            onChange={(event: React.FormEvent<HTMLInputElement>) => {
              const { value } = event.currentTarget;
              setUserInfo({ ...userInfo, [infoKey]: value });
            }}
          />
        </div>
      ))}
      <Button
        buttonStyle="STANDARD"
        label="회원가입"
        size="LARGE"
        HandleOnClick={handleClickSignUpButton}
        disabled={!isFilled()}
      />
    </StyledSignUpForm>
  );
};

export default SignUpForm;
