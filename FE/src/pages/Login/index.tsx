import { useEffect, useState } from 'react';
import Button from '@/components/Button';
import Logo from '@/components/Logo';
import TextInput from '@/components/TextInput';
import InputMessage from '@/components/InputMessage';
import useInputTextValue from '@/hooks/useInputTextValue';
import { InputName, LimitLength, Error } from '@/pages/Login/type';
import Layout from '@/layout';
import {
  $LogoWrapper,
  $Contents,
  $Form,
  $Span,
  $LoginInputWrap,
  $ButtonWrapper
} from '@/pages/Login/style';
import { COLOR } from '@/styles/common';

const TEXT_INPUT_DEBOUNCE_TIME = 300;

const LIMIT_LENGTH: Record<InputName, LimitLength> = {
  id: { min: 6, max: 16 },
  pw: { min: 6, max: 12 }
};

const ERROR_MESSAGE = {
  successID: () => '사용 가능한 아이디 입니다.',
  errorID: () => '이미 사용하고 있는 아이디 입니다.',
  lengthCheck: (min: number, max: number) => `최소 ${min}자리에서 ${max}까지 입력하세요.`
};

const initialError = {
  status: null,
  message: ''
};

export default function Login() {
  const { inputInfo, updateInputValue, checkInputValueLength } = useInputTextValue('');
  const [error, setError] = useState<Error>(initialError);

  const toggleLengthErrorMessage = () => {
    if (!inputInfo) return;

    const isRightLength = checkInputValueLength(LIMIT_LENGTH.id.min, LIMIT_LENGTH.id.max);
    if (isRightLength) {
      setError(initialError);
    } else {
      const message = ERROR_MESSAGE.lengthCheck(LIMIT_LENGTH.id.min, LIMIT_LENGTH.id.max);
      setError({ status: 'lengthCheck', message: message });
    }
  };

  useEffect(() => {
    if (!inputInfo.value) return;
    toggleLengthErrorMessage();
  }, [inputInfo]);

  return (
    <Layout>
      <$Contents>
        <$LogoWrapper>
          <Logo type="large" />
        </$LogoWrapper>
        <Button styleType="large" text="GitHub 계정으로 로그인" background={COLOR.title} />
        <$Span>or</$Span>
        <$Form>
          <$LoginInputWrap>
            <TextInput
              styleType="large"
              placeholder="아이디"
              label="아이디"
              name="id"
              handleChange={(event: React.ChangeEvent<HTMLInputElement>) =>
                updateInputValue(event, TEXT_INPUT_DEBOUNCE_TIME)
              }
            />
            {inputInfo.name === 'id'
              ? inputInfo.value &&
                error.status && <InputMessage status={error.status}>{error.message}</InputMessage>
              : null}
          </$LoginInputWrap>
          <$LoginInputWrap>
            <TextInput
              styleType="large"
              placeholder="비밀번호"
              label="비밀번호"
              name="pw"
              handleChange={(event: React.ChangeEvent<HTMLInputElement>) =>
                updateInputValue(event, TEXT_INPUT_DEBOUNCE_TIME)
              }
              type="password"
            />
            {inputInfo.name === 'pw'
              ? inputInfo.value &&
                error.status && <InputMessage status={error.status}>{error.message}</InputMessage>
              : null}
          </$LoginInputWrap>
        </$Form>
        <$ButtonWrapper>
          <Button styleType="large" text="아이디로 로그인" disabled={true} />
          <Button styleType="smallText" text="회원가입" />
        </$ButtonWrapper>
      </$Contents>
    </Layout>
  );
}
