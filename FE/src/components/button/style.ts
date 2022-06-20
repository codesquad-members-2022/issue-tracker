import {
  IButtonStyleProps,
  ButtonStyle,
  ButtonStyleType,
  I$ButtonType
} from '@/components/Button/type';
import styled, { css } from 'styled-components';
import { darken, lighten } from 'polished';

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

const standardEventStyle = css<I$ButtonType>`
  :enabled:hover {
    background: ${({ theme, background }) =>
      !background ? theme.COLOR.primary.hover : darken(0.1, background)};
  }
  :enabled:active {
    background: ${({ theme, background }) =>
      !background ? theme.COLOR.primary.initial : background};
    border: 4px solid
      ${({ theme, background }) =>
        !background ? theme.COLOR.primary.focus : lighten(0.3, background)};
  }
  :disabled {
    opacity: 50%;
  }
`;

const secondaryEventStyle = css<I$ButtonType>`
  :hover {
    color: ${({ theme, color }) => (!color ? theme.COLOR.primary.hover : darken(0.1, color))};
    border-color: ${({ theme, color }) =>
      !color ? theme.COLOR.primary.hover : darken(0.1, color)};
    svg > path {
      stroke: ${({ theme, color }) => (!color ? theme.COLOR.primary.hover : darken(0.1, color))};
    }
  }
  :enabled:active {
    color: ${({ theme, color }) => (!color ? theme.COLOR.primary.initial : lighten(0.1, color))};
    border: 4px solid
      ${({ theme, color }) => (!color ? theme.COLOR.primary.focus : lighten(0.3, color))};
    svg > path {
      stroke: ${({ theme, color }) => (!color ? theme.COLOR.primary.initial : lighten(0.1, color))};
    }
  }
  :disabled {
    opacity: 50%;
  }
`;

const textEventStyle = css<I$ButtonType>`
  :hover {
    color: ${({ theme, color }) => (!color ? theme.COLOR.body : darken(0.1, color))};
    svg > path {
      stroke: ${({ theme, color }) => (!color ? theme.COLOR.body : darken(0.1, color))};
    }
  }
  :enabled:active {
    color: ${({ theme, color }) => (!color ? theme.COLOR.title : darken(0.2, color))};
    svg > path {
      stroke: ${({ theme, color }) => (!color ? theme.COLOR.title : darken(0.2, color))};
    }
  }
  :disabled {
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
  ${smallFontStyle}
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
  ${props.color && CustomColorStyle}
  ${props.background && { background: props.background }}
  ${props.border && { border: props.border }}
  ${props.borderRadius && { 'border-radius': props.borderRadius }}
  ${props.fontSize && { 'font-size': props.fontSize }}
  ${props.fontWeight && { 'font-weight': props.fontWeight }}
`;

const CustomColorStyle = css<I$ButtonType>`
  color: ${({ color }) => color};
  svg > path {
    stroke: ${({ color }) => color};
  }
  border-color: ${({ color }) => color};
`;

const $Button = styled.button<I$ButtonType>`
  display: flex;
  justify-content: center;
  align-items: center;
  ${({ styleType = 'large', ...props }) => createCustomStyle(styleType, props)}
`;

const $TextWrapper = styled.span`
  padding-left: 4px;
`;

export { $Button, $TextWrapper };
