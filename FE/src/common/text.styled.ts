import { css, DefaultTheme } from 'styled-components';

const handleFontWeightType = (
  theme: DefaultTheme,
  fontWeight: string,
): string => {
  switch (fontWeight) {
    case 'normal':
      return theme.fontWeights.normal;
    case 'bold':
      return theme.fontWeights.bold;
    default:
      return '';
  }
};

const handleFontSizeType = (theme: DefaultTheme, fontSize: string): string => {
  switch (fontSize) {
    case 'xs':
      return theme.fontSizes.xs;
    case 'sm':
      return theme.fontSizes.sm;
    case 'md':
      return theme.fontSizes.md;
    case 'lg':
      return theme.fontSizes.lg;
    case 'xl':
      return theme.fontSizes.xl;
    default:
      return '';
  }
};

const handleLineHeightType = (
  theme: DefaultTheme,
  lineHeight: string,
): string => {
  switch (lineHeight) {
    case 'shorter':
      return theme.lineHeights.shorter;
    case 'short':
      return theme.lineHeights.short;
    case 'base':
      return theme.lineHeights.base;
    case 'tall':
      return theme.lineHeights.tall;
    case 'taller':
      return theme.lineHeights.taller;
    default:
      return '';
  }
};

const handleColorType = (theme: DefaultTheme, color: string): string => {
  switch (color) {
    case 'titleActive':
      return theme.colors.titleActive;
    case 'body':
      return theme.colors.body;
    case 'label':
      return theme.colors.label;
    case 'placeholder':
      return theme.colors.placeholder;
    case 'line':
      return theme.colors.line;
    case 'inputBackground':
      return theme.colors.inputBackground;
    case 'background':
      return theme.colors.background;
    case 'offWhite':
      return theme.colors.offWhite;
    case 'primary':
      return theme.colors.primary;
    case 'primaryLight':
      return theme.colors.primaryLight;
    case 'primaryDark':
      return theme.colors.primaryDark;
    case 'secondary':
      return theme.colors.secondary;
    case 'secondaryLight':
      return theme.colors.secondaryLight;
    case 'secondaryDark':
      return theme.colors.secondaryDark;
    case 'error':
      return theme.colors.error;
    case 'errorLight':
      return theme.colors.errorLight;
    case 'errorDark':
      return theme.colors.errorDark;
    case 'success':
      return theme.colors.success;
    case 'successLight':
      return theme.colors.successLight;
    case 'successDark':
      return theme.colors.successDark;
    case 'grey1':
      return theme.colors.grey1;
    default:
      return '';
  }
};

export const TextStyles = css<{
  fontWeight: string;
  fontSize: string;
  lineHeight: string;
  color: string;
}>`
  ${({ theme, fontWeight, fontSize, lineHeight, color }) => css`
    font-weight: ${handleFontWeightType(theme, fontWeight)};
    font-size: ${handleFontSizeType(theme, fontSize)};
    line-height: ${handleLineHeightType(theme, lineHeight)};
    color: ${handleColorType(theme, color)};
  `}
`;
