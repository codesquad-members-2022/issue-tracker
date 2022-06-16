import {
  IButtonStyleProps,
  ButtonStyle,
  ButtonStyleType,
  IStyled_buttonType
} from '@/components/Button/type';
import styled, { css } from 'styled-components';

const standardColorStyle = css`
  color: ${({ theme }) => theme.PALETTE.WHITE};
  background: ${({ theme }) => theme.COLOR.primary.initial};
  svg > path {
    stroke: ${({ theme }) => theme.PALETTE.WHITE};
  }
`;

const secondaryColorStyle = css`
  color: ${({ theme }) => theme.COLOR.primary.initial};
  background: ${({ theme }) => theme.PALETTE.WHITE};
  border-color: ${({ theme }) => theme.COLOR.primary.initial};
  svg > path {
    stroke: ${({ theme }) => theme.COLOR.primary.initial};
  }
`;

const textColorStyle = css`
  color: ${({ theme }) => theme.COLOR.label};
  svg > path {
    stroke: ${({ theme }) => theme.COLOR.label};
  }
`;

const largeFontStyle = css`
  font-size: ${({ theme }) => `${theme.FONT.SIZE.MEDIUM}`};
  font-weight: ${({ theme }) => `${theme.FONT.WEIGHT.BOLD}`};
`;

const mediumFontStyle = css`
  font-size: ${({ theme }) => `${theme.FONT.SIZE.BASE}`};
  font-weight: ${({ theme }) => `${theme.FONT.WEIGHT.BOLD}`};
`;

const smallFontStyle = css`
  font-size: ${({ theme }) => `${theme.FONT.SIZE.X_SMALL}`};
  font-weight: ${({ theme }) => `${theme.FONT.WEIGHT.BOLD}`};
`;

const standardEventStyle = css`
  :hover {
    background: ${({ theme }) => theme.COLOR.primary.hover};
  }
  :active {
    background: ${({ theme }) => theme.COLOR.primary.initial};
    border: 4px solid ${({ theme }) => theme.COLOR.primary.focus};
  }
  :disabled {
    background: ${({ theme }) => theme.COLOR.primary.initial};
    opacity: 50%;
  }
`;

const secondaryEventStyle = css`
  :hover {
    color: ${({ theme }) => theme.COLOR.primary.hover};
    border-color: ${({ theme }) => theme.COLOR.primary.hover};
    svg > path {
      stroke: ${({ theme }) => theme.COLOR.primary.hover};
    }
  }
  :active {
    color: ${({ theme }) => theme.COLOR.primary.initial};
    border: 4px solid ${({ theme }) => theme.COLOR.primary.focus};
    svg > path {
      stroke: ${({ theme }) => theme.COLOR.primary.initial};
    }
  }
  :disabled {
    opacity: 50%;
  }
`;

const textEventStyle = css`
  :hover {
    color: ${({ theme }) => theme.COLOR.body};
    svg > path {
      stroke: ${({ theme }) => theme.COLOR.body};
    }
  }
  :active {
    color: ${({ theme }) => theme.COLOR.title};
    svg > path {
      stroke: ${({ theme }) => theme.COLOR.title};
    }
  }
  :disabled {
    color: ${({ theme }) => theme.COLOR.body};
    opacity: 50%;
  }
`;

const large = css`
  width: 340px;
  height: 64px;
  border-radius: 20px;
  ${standardColorStyle}
  ${standardEventStyle}
  ${largeFontStyle}
`;

const mediumStandard = css`
  width: 240px;
  height: 56px;
  border-radius: 20px;
  ${standardColorStyle}
  ${standardEventStyle}
  ${largeFontStyle}
`;

const smallStandard = css`
  width: 120px;
  height: 40px;
  border-radius: 11px;
  ${standardColorStyle}
  ${standardEventStyle}
  ${smallFontStyle}
`;

const smallSecondary = css`
  width: 120px;
  height: 40px;
  border: 2px solid;
  border-radius: 11px;
  ${secondaryColorStyle}
  ${secondaryEventStyle}
`;

const mediumText = css`
  width: 87px;
  height: 32px;
  ${textColorStyle}
  ${textEventStyle}
  ${mediumFontStyle}
`;

const smallText = css`
  width: 70px;
  height: 32px;
  ${textColorStyle}
  ${textEventStyle}
  ${textEventStyle}
`;

const buttonStyle: ButtonStyle = {
  large: large,
  smallStandard: smallStandard,
  mediumStandard: mediumStandard,
  smallSecondary: smallSecondary,
  mediumText: mediumText,
  smallText: smallText
};

const createCustomStyle = (styleType: ButtonStyleType, props: IButtonStyleProps) => css`
  ${styleType && buttonStyle[styleType]}
  ${props.width && { width: props.width }}
  ${props.height && { height: props.height }}
  ${props.color && { color: props.color }}
  ${props.background && { background: props.background }}
  ${props.border && { border: props.border }}
  ${props.borderRadius && { 'border-radius': props.borderRadius }}
  ${props.fontSize && { 'font-size': props.fontSize }}
  ${props.fontWeight && { 'font-weight': props.fontWeight }}
`;

const Styled_button = styled.button<IStyled_buttonType>`
  display: flex;
  justify-content: center;
  align-items: center;
  ${({ styleType = 'large', ...props }) => createCustomStyle(styleType, props)}
`;

const Styled_TextWrapper = styled.span`
  padding-left: 4px;
`;

export { Styled_button, Styled_TextWrapper };
