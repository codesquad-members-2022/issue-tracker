import Button from '@/components/Button';
import Logo from '@/components/Logo';
import TextInput from '@/components/TextInput';
import Layout from '@/layout';
import {
  Styled_contents,
  Styled_loginInput,
  Styled_logoWrapper,
  Styled_form,
  Styled_span,
  Styled_buttonWrapper
} from '@/pages/Login/style';
import { COLOR } from '@/styles/common';

export default function Login() {
  return (
    <Layout>
      <Styled_contents>
        <Styled_logoWrapper>
          <Logo type="large" />
        </Styled_logoWrapper>
        <Button styleType="large" text="GitHub 계정으로 로그인" background={COLOR.title} />
        <Styled_span>or</Styled_span>
        <Styled_form>
          <TextInput styleType="large" placeholder="아이디" as={Styled_loginInput} />
          <TextInput
            styleType="large"
            placeholder="비밀번호"
            as={Styled_loginInput}
            type="password"
          />
        </Styled_form>
        <Styled_buttonWrapper>
          <Button styleType="large" text="아이디로 로그인" disabled={true} />
          <Button styleType="smallText" text="회원가입" />
        </Styled_buttonWrapper>
      </Styled_contents>
    </Layout>
  );
}
