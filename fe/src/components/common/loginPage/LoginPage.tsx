import React from "react";
import styled, { css } from "styled-components";

import { buttonStyle } from "constants/buttonStyle";
import { loginPageText } from "constants/loginPageText";
import Button from "../button/Button";
import TextButton from "../button/TextButton";
import Input from "../Input";
import Logo from "../Logo";

const LOGIN_URL =
  "https://github.com/login/oauth/authorize/?client_id=2f2f8ccb4f67b255a805&redirect_uri=http://localhost:3000/oauth/callback";

function LoginPage() {
  const handleLoginButtonClick = () => {
    window.location.href = LOGIN_URL;
  };

  return (
    <StyledLoginPage>
      <Logo size="large" />
      <LoginForm>
        <GithubLoginButton type="button" onClick={handleLoginButtonClick}>
          {loginPageText.btn.github}
        </GithubLoginButton>
        or
        <Input size="large" type="initial" text={loginPageText.input.id} />
        <Input size="large" type="initial" text={loginPageText.input.password} />
        <Button size="large" set="standard" text={loginPageText.btn.id} />
        <TextButton text="회원가입" />
      </LoginForm>
    </StyledLoginPage>
  );
}

const StyledLoginPage = styled.div`
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 340px;
  margin: 0 auto;
  text-align: center;
`;

const LoginForm = styled.form`
  display: flex;
  align-items: center;
  flex-direction: column;
  gap: 20px;
  margin-top: 30px;
  width: 100%;
  color: ${({ theme }) => theme.colors.placeHolder};
`;

const GithubLoginButton = styled.button`
  ${({ theme: { colors, fontSize, fontWeight } }) => {
    const { width, height } = buttonStyle.size.large;
    const { large: ftSize } = buttonStyle.fontSize;
    const { large: borderRadius } = buttonStyle.borderRadius;

    return css`
      width: ${width}px;
      height: ${height}px;
      background: ${colors.titleActive};
      color: ${colors.offWhite};
      font-size: ${fontSize[ftSize]};
      font-weight: ${fontWeight.bold};
      border-radius: ${borderRadius}px;
    `;
  }}
`;

export default LoginPage;
