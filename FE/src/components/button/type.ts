import { FlattenInterpolation, ThemeProps } from 'styled-components';
import { IconTypes } from '@/components/Icon';

type ButtonStyleType =
  | 'Large'
  | 'Small-Standard'
  | 'Medium-Standard'
  | 'Small-Secondary'
  | 'Medium-Text'
  | 'Small-Text';

type ButtonStyle = Record<ButtonStyleType, FlattenInterpolation<ThemeProps<any>>>;

interface ButtonStyleProps {
  width?: string;
  height?: string;
  color?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
  fontSize?: string;
  fontWeight?: string;
}

interface ButtonProps extends ButtonStyleProps {
  styleType: ButtonStyleType;
  iconType?: IconTypes;
  iconColor?: string;
  text?: string;
  children?: React.ReactNode;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

interface Styled_buttonType extends ButtonStyleProps {
  styleType: ButtonStyleType;
}

export type { ButtonProps, ButtonStyleType, ButtonStyleProps, ButtonStyle, Styled_buttonType };
