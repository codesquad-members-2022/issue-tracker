import { Styled_textInput } from '@/components/TextInput/style';
import styled from 'styled-components';

const Styled_logoWrapper = styled.div`
  margin-bottom: 60px;
`;

const Styled_loginInput = styled(Styled_textInput)`
  & + & {
    margin-top: 16px;
  }
`;

const Styled_contents = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
`;

const Styled_form = styled.form`
  margin-bottom: 24px;
`;

const Styled_span = styled.span`
  color: ${({ theme }) => theme.COLOR.placeholder};
  font-size: ${({ theme }) => theme.FONT.SIZE.BASE};
  font-weight: ${({ theme }) => theme.FONT.WEIGHT.BOLD};
  margin: 24px 0;
`;

const Styled_buttonWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  button + button {
    margin-top: 16px;
  }
`;

export {
  Styled_contents,
  Styled_loginInput,
  Styled_logoWrapper,
  Styled_form,
  Styled_span,
  Styled_buttonWrapper
};
