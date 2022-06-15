import { FlattenInterpolation, ThemeProps } from 'styled-components';
import { IconTypes } from '@/components/Icon';

type ButtonStyleType =
  | 'large'
  | 'smallStandard'
  | 'mediumStandard'
  | 'smallSecondary'
  | 'mediumText'
  | 'smallText';

type ButtonStyle = Record<ButtonStyleType, FlattenInterpolation<ThemeProps<any>>>;

interface IButtonStyleProps {
  width?: string;
  height?: string;
  color?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
  fontSize?: string;
  fontWeight?: string;
}

interface IButtonProps extends IButtonStyleProps {
  styleType?: ButtonStyleType;
  iconType?: IconTypes;
  iconColor?: string;
  text?: string;
  children?: React.ReactNode;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

interface IStyled_buttonType extends IButtonStyleProps {
  styleType?: ButtonStyleType;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

export type { IButtonProps, ButtonStyleType, IButtonStyleProps, ButtonStyle, IStyled_buttonType };
