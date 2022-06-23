import React from "react";
import { Link } from "react-router-dom";
import styled, { css } from "styled-components";

import { buttonStyle } from "constants/buttonStyle";
import { loginPageText } from "constants/loginPageText";
import Button from "../button/Button";
import TextButton from "../button/TextButton";
import Input from "../Input";
import Logo from "../Logo";

function LoginPage() {
  return (
    <StyledLoginPage>
      <Logo size="large" />
      <LoginForm>
        <Link to="/issues">
          <GithubLoginButton>{loginPageText.btn.github}</GithubLoginButton>
        </Link>
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
