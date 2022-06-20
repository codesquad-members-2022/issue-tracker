import styled from 'styled-components';
import { ILabelProps } from '@/components/Label/type';

const $Label = styled.span<ILabelProps>`
  display: flex;
  align-items: center;
  width: fit-content;
  padding: 0 16px;
  font-size: ${({ theme }) => theme.FONT.SIZE.X_SMALL};
  line-height: 38px;
  color: ${({ theme, status }) => theme.LABEL[status].color};
  background: ${({ theme, status }) => theme.LABEL[status].background};
  border: ${({ theme, status }) => `1px solid ${theme.LABEL[status].color}`};
  border-radius: 20px;
  svg {
    margin-right: 5px;
  }
`;

export { $Label };
