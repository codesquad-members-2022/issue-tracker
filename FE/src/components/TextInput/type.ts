import { FlattenInterpolation, ThemeProps } from 'styled-components';

type StyleType = 'large' | 'medium' | 'small';

interface IStyleProps {
  width?: string;
  height?: string;
  color?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
  fontSize?: string;
  fontWeight?: string;
}

type StyleTypes = Record<StyleType, FlattenInterpolation<ThemeProps<any>>>;

interface IStyled_textInput extends IStyleProps {
  styleType?: StyleType;
}

export type { StyleType, IStyleProps, StyleTypes, IStyled_textInput };
