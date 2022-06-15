import styled, { css } from 'styled-components';
import { StyleType, IStyleProps, StyleTypes, IStyled_textInput } from '@/components/TextInput/type';

const standardEventStyle = css`
  :active {
    background: ${({ theme }) => theme.PALETTE.WHITE};
    border: 1px solid ${({ theme }) => theme.COLOR.title};
  }
  :disabled {
    opacity: 50%;
  }
`;

const large = css`
  width: 340px;
  line-height: 60px;
  border-radius: 16px;
  ${standardEventStyle}
`;

const medium = css`
  width: 320px;
  line-height: 56px;
  border-radius: 14px;
  ${standardEventStyle}
`;

const small = css`
  width: 87px;
  height: 32px;
  border-radius: 11px;
  ${standardEventStyle}
`;

const styleTypes: StyleTypes = {
  large: large,
  medium: medium,
  small: small
};

const createCustomStyle = (styleType: StyleType, props: IStyleProps) => css`
  ${styleType && styleTypes[styleType]}
  ${props.width && { width: props.width }}
  ${props.height && { height: props.height }}
  ${props.color && { color: props.color }}
  ${props.background && { background: props.background }}
  ${props.border && { border: props.border }}
  ${props.borderRadius && { 'border-radius': props.borderRadius }}
`;

const Styled_textInput = styled.input<IStyled_textInput>`
  padding: 0 24px;
  color: ${({ theme }) => theme.COLOR.title};
  background: ${({ theme }) => theme.COLOR.inputBackground};
  ${({ styleType = 'large', ...props }) => createCustomStyle(styleType, props)}
`;

export { Styled_textInput };
