import styled from 'styled-components';
import { IInputMessageProps } from '@/components/InputMessage/type';

const Styled_message = styled.p<IInputMessageProps>`
  margin-top: 6px;
  font-size: ${({ theme }) => theme.FONT.SIZE.X_SMALL};
  color: ${({ theme, status }) => (status ? theme.COLOR[status].text : 'inherit')};
`;

export { Styled_message };
