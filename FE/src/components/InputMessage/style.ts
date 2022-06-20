import styled from 'styled-components';
import { IInputMessageProps } from '@/components/InputMessage/type';

const $Message = styled.p<IInputMessageProps>`
  margin-top: 6px;
  font-size: ${({ theme }) => theme.FONT.SIZE.X_SMALL};
  color: ${({ theme, status }) => (status ? theme.ERROR[status].text : 'inherit')};
`;

export { $Message };
