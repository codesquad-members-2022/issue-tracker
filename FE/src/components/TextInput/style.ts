import styled, { css } from 'styled-components';
import {
  StyleType,
  IStyleProps,
  IStyled_label,
  IStyled_textInput
} from '@/components/TextInput/type';

const standardEventStyle = css`
  :focus {
    background: ${({ theme }) => theme.PALETTE.WHITE};
    border: 1px solid ${({ theme }) => theme.COLOR.title};
  }
  :disabled {
    opacity: 50%;
  }
`;

const largeTextInput = css<IStyled_textInput>`
  width: 340px;
  height: 60px;
  padding: ${({ visibleLabel }) => (visibleLabel ? '28px 24px 8px' : '16px 24px')};
  border-radius: 16px;
  :focus {
    padding: ${({ visibleLabel }) => (visibleLabel ? '27px 23px 7px' : '15px 23px')};
  }
  ${standardEventStyle}
`;

const largeLabel = css`
  top: 8px;
`;

const mediumTextInput = css<IStyled_textInput>`
  width: 320px;
  height: 56px;
  padding: ${({ visibleLabel }) => (visibleLabel ? '24px 24px 4px' : '14px 24px')};
  border-radius: 14px;
  :focus {
    padding: ${({ visibleLabel }) => (visibleLabel ? '23px 23px 3px' : '13px 23px')};
  }
  ${standardEventStyle}
`;

const mediumLabel = css`
  top: 4px;
`;

const smallTextInput = css<IStyled_textInput>`
  width: 300px;
  height: 40px;
  padding: ${({ visibleLabel }) => (visibleLabel ? '6px 24px 6px 112px' : '6px 24px')};
  border-radius: 11px;
  :focus {
    padding: ${({ visibleLabel }) => (visibleLabel ? '5px 23px 5px 111px' : '5px 23px')};
    transition: none;
  }
  ${standardEventStyle}
`;

const smallLabel = css`
  top: 50%;
  transform: translateY(-50%);
`;

const getStyleTypes = (styleType: StyleType) => {
  switch (styleType) {
    case 'large':
      return {
        textInput: largeTextInput,
        label: largeLabel
      };
    case 'medium':
      return {
        textInput: mediumTextInput,
        label: mediumLabel
      };
    case 'small':
      return {
        textInput: smallTextInput,
        label: smallLabel
      };
  }
};

const createCustomLabelStyle = (styleType: StyleType) => css`
  ${getStyleTypes(styleType).label}
`;

const createCustomTextInputStyle = (styleType: StyleType, props: IStyleProps) => css`
  ${getStyleTypes(styleType).textInput}
  ${props.width && { width: props.width }}
  ${props.height && { height: props.height }}
  ${props.color && { color: props.color }}
  ${props.background && { background: props.background }}
  ${props.border && { border: props.border }}
  ${props.borderRadius && { 'border-radius': props.borderRadius }}
`;

const Styled_textInputWrap = styled.div`
  position: relative;
`;

const Styled_label = styled.label<IStyled_label>`
  position: absolute;
  left: 24px;
  display: ${({ visible }) => (visible ? 'block' : 'none')};
  font-size: ${({ theme }) => theme.FONT.SIZE.X_SMALL};
  color: ${({ theme }) => theme.COLOR.label};
  ${({ styleType = 'large' }) => createCustomLabelStyle(styleType)}
`;

const Styled_textInput = styled.input<IStyled_textInput>`
  color: ${({ theme }) => theme.COLOR.title};
  background: ${({ theme }) => theme.COLOR.inputBackground};
  &::placeholder {
    color: ${({ theme }) => theme.COLOR.placeholder};
  }
  :focus {
    transition: ${({ visibleLabel }) => (visibleLabel ? 'padding 0.15s ease-in' : 'none')};
  }
  ${({ styleType = 'large', ...props }) => createCustomTextInputStyle(styleType, props)}
`;

export { Styled_textInputWrap, Styled_label, Styled_textInput };
