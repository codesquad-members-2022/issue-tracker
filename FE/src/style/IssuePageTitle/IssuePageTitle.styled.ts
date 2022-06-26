import { TextStyles } from 'common/text.styled';
import styled from 'styled-components';

export const Title = styled.h2<{
  fontWeight: string;
  fontSize: string;
  lineHeight: string;
  color: string;
}>`
  ${TextStyles}
  margin-bottom: 32px;
  padding: 32px 0;
  border-bottom: 1px solid ${({ theme: { colors } }) => colors.grey1};
`;
