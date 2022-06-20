import styled, { css } from 'styled-components';
import { Size, ILabelProps } from '@/components/Label/type';

const large = css<ILabelProps>`
  height: 40px;
  border-radius: 20px;
`;

const small = css<ILabelProps>`
  height: 28px;
  border-radius: 14px;
`;

const getSizeStyle = (size: Size) => {
  switch (size) {
    case 'large':
      return large;
    case 'small':
      return small;
  }
};

const $Label = styled.span<ILabelProps>`
  display: flex;
  align-items: center;
  width: fit-content;
  padding: 0 16px;
  font-size: ${({ theme }) => theme.FONT.SIZE.X_SMALL};
  color: ${({ theme, status }) => theme.LABEL[status].color};
  background: ${({ theme, status }) => theme.LABEL[status].background};
  border: ${({ theme, status }) =>
    theme.LABEL[status].border ? `1px solid ${theme.LABEL[status].border}` : 0};
  svg {
    margin-right: 5px;
  }
  ${({ size = 'large' }) => getSizeStyle(size)}
`;

export { $Label };
