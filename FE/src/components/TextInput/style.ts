import styled, { css } from 'styled-components';
import { StyleType, IStyleProps, I$Label, I$TextInput } from '@/components/TextInput/type';

const commonEventStyle = css`
  :focus {
    background: ${({ theme }) => theme.PALETTE.WHITE};
    border: 1px solid ${({ theme }) => theme.COLOR.title};
  }
  :disabled {
    opacity: 50%;
  }
`;

const largeTextInput = css<I$TextInput>`
  width: 340px;
  height: 60px;
  padding: ${({ visibleLabel }) => (visibleLabel ? '28px 24px 8px' : '16px 24px')};
  border-radius: 16px;
  background: ${({ theme, status }) =>
    status ? theme.COLOR[status].background : theme.COLOR.inputBackground};
  border: ${({ theme, status }) => (status ? `1px solid ${theme.COLOR[status].border}` : 0)};
  ${commonEventStyle}
  :focus {
    padding: ${({ visibleLabel }) => (visibleLabel ? '27px 23px 7px' : '15px 23px')};
  }
`;

const largeLabel = css<I$Label>`
  top: 8px;
  color: ${({ theme, status }) => (status ? theme.COLOR[status].text : theme.COLOR.label)};
`;

const mediumTextInput = css<I$TextInput>`
  width: 320px;
  height: 56px;
  padding: ${({ visibleLabel }) => (visibleLabel ? '24px 24px 4px' : '14px 24px')};
  border-radius: 14px;
  background: ${({ theme }) => theme.COLOR.inputBackground};
  ${commonEventStyle}
  :focus {
    padding: ${({ visibleLabel }) => (visibleLabel ? '23px 23px 3px' : '13px 23px')};
  }
`;

const mediumLabel = css`
  top: 4px;
  color: ${({ theme }) => theme.COLOR.label};
`;

const smallTextInput = css<I$TextInput>`
  width: 300px;
  height: 40px;
  padding: ${({ visibleLabel }) => (visibleLabel ? '6px 24px 6px 112px' : '6px 24px')};
  border-radius: 11px;
  background: ${({ theme, status }) =>
    status ? theme.COLOR[status].background : theme.COLOR.inputBackground};
  border: ${({ theme, status }) => (status ? `1px solid ${theme.COLOR[status].border}` : 0)};
  ${commonEventStyle}
  :focus {
    padding: ${({ visibleLabel }) => (visibleLabel ? '5px 23px 5px 111px' : '5px 23px')};
    transition: none;
  }
`;

const smallLabel = css<I$Label>`
  top: 50%;
  transform: translateY(-50%);
  color: ${({ theme, status }) => (status ? theme.COLOR[status].text : theme.COLOR.label)};
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

const $TextInputWrap = styled.div`
  position: relative;
`;

const $Label = styled.label<I$Label>`
  position: absolute;
  left: 24px;
  display: ${({ visible }) => (visible ? 'block' : 'none')};
  font-size: ${({ theme }) => theme.FONT.SIZE.X_SMALL};
  ${({ styleType = 'large' }) => createCustomLabelStyle(styleType)}
`;

const $TextInput = styled.input<I$TextInput>`
  color: ${({ theme }) => theme.COLOR.title};
  &::placeholder {
    color: ${({ theme }) => theme.COLOR.placeholder};
  }
  :focus {
    transition: ${({ visibleLabel }) => (visibleLabel ? 'padding 0.15s ease-in' : 'none')};
  }
  ${({ styleType = 'large', ...props }) => createCustomTextInputStyle(styleType, props)}
`;

export { $TextInputWrap, $Label, $TextInput };
