import { $TextInputWrap } from '@/components/common/TextInput/style';
import styled from 'styled-components';

const $LogoWrapper = styled.div`
  margin-bottom: 60px;
`;

const $Contents = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
`;

const $LoginForm = styled.form`
  margin-bottom: 24px;
`;

const $DecoText = styled.span`
  color: ${({ theme }) => theme.COLOR.placeholder};
  font-size: ${({ theme }) => theme.FONT.SIZE.BASE};
  font-weight: ${({ theme }) => theme.FONT.WEIGHT.BOLD};
  margin: 24px 0;
`;

const $LoginInputWrap = styled($TextInputWrap)`
  & + & {
    margin-top: 16px;
  }
`;

const $ButtonWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  button + button {
    margin-top: 16px;
  }
`;

export { $LogoWrapper, $Contents, $LoginForm, $DecoText, $LoginInputWrap, $ButtonWrapper };
