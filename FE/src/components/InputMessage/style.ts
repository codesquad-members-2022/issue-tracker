import styled from 'styled-components';
import { IStyled_message } from './type';

const Styled_message = styled.p<IStyled_message>`
  margin-top: 6px;
  font-size: ${({ theme }) => theme.FONT.SIZE.X_SMALL};
  color: ${({ theme, status }) => (status ? theme.COLOR[status].text : 'inherit')};
`;

export { Styled_message };
