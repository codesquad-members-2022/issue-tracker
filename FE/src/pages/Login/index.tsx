import { useEffect, useState } from 'react';
import Button from '@/components/Button';
import Logo from '@/components/Logo';
import TextInput from '@/components/TextInput';
import InputMessage from '@/components/InputMessage';
import useInputTextValue from '@/hooks/useInputTextValue';
import { InputName, Error } from '@/pages/Login/type';
import Layout from '@/layout';
import {
  $LogoWrapper,
  $Contents,
  $LoginForm,
  $DecoText,
  $LoginInputWrap,
  $ButtonWrapper
} from '@/pages/Login/style';
import { COLOR } from '@/styles/common';

const TEXT_INPUT_DEBOUNCE_TIME = 300;

const LIMIT_LENGTH = {
  id: { min: 6, max: 16 },
  pw: { min: 6, max: 12 }
};

const ERROR_MESSAGE = {
  id: {
    success: '사용 가능한 아이디 입니다.',
    error: '이미 사용하고 있는 아이디 입니다.',
    lengthCheck: `최소 ${LIMIT_LENGTH.id.min}자리에서 ${LIMIT_LENGTH.id.max}까지 입력하세요.`
  },
  pw: {
    success: '사용 가능한 비밀번호 입니다.',
    lengthCheck: `최소 ${LIMIT_LENGTH.pw.min}자리에서 ${LIMIT_LENGTH.pw.max}까지 입력하세요.`
  }
};

const initialError = {
  status: null,
  message: ''
};

export default function Login() {
  const { inputInfo, updateInputValue, checkInputValueLength } = useInputTextValue<InputName>('');
  const [error, setError] = useState<Error>({ id: initialError, pw: initialError });

  const setLengthError = () => {
    if (!inputInfo.name) return;

    const isRightLength = checkInputValueLength(
      LIMIT_LENGTH[inputInfo.name].min,
      LIMIT_LENGTH[inputInfo.name].max
    );
    const newError = { ...error };

    if (isRightLength) {
      newError[inputInfo.name] = {
        status: 'success',
        message: ERROR_MESSAGE[inputInfo.name].success
      };
    } else {
      newError[inputInfo.name] = {
        status: 'lengthCheck',
        message: ERROR_MESSAGE[inputInfo.name].lengthCheck
      };
    }

    setError(newError);
  };

  useEffect(setLengthError, [inputInfo]);

  const hasError = () => {
    const inputNames = Object.keys(error) as InputName[];
    const errorStatus = inputNames.find((name: InputName) => error[name].status !== 'success');
    return errorStatus !== undefined;
  };

  return (
    <Layout>
      <$Contents>
        <$LogoWrapper>
          <Logo type="large" />
        </$LogoWrapper>
        <Button styleType="large" text="GitHub 계정으로 로그인" background={COLOR.title} />
        <$DecoText>or</$DecoText>
        <$LoginForm>
          <$LoginInputWrap>
            <TextInput<InputName>
              styleType="large"
              placeholder="아이디"
              label="아이디"
              name="id"
              status={error.id.status}
              handleChange={({ name, value }: { name: InputName; value: string }) =>
                updateInputValue(name, value, TEXT_INPUT_DEBOUNCE_TIME)
              }
            />
            {error.id.status && (
              <InputMessage status={error.id.status}>{error.id.message}</InputMessage>
            )}
          </$LoginInputWrap>
          <$LoginInputWrap>
            <TextInput<InputName>
              styleType="large"
              placeholder="비밀번호"
              label="비밀번호"
              name="pw"
              status={error.pw.status}
              handleChange={({ name, value }: { name: InputName; value: string }) =>
                updateInputValue(name, value, TEXT_INPUT_DEBOUNCE_TIME)
              }
              type="password"
            />
            {error.pw.status && (
              <InputMessage status={error.pw.status}>{error.pw.message}</InputMessage>
            )}
          </$LoginInputWrap>
        </$LoginForm>
        <$ButtonWrapper>
          <Button styleType="large" text="아이디로 로그인" disabled={hasError()} />
          <Button styleType="smallText" text="회원가입" />
        </$ButtonWrapper>
      </$Contents>
    </Layout>
  );
}
