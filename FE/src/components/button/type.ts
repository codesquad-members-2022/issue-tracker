import { FlattenInterpolation, ThemeProps, StyledComponent } from 'styled-components';
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
  iconColor?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
  fontSize?: string;
  fontWeight?: string;
}

interface IButtonProps extends IButtonStyleProps {
  styleType?: ButtonStyleType;
  iconType?: IconTypes;
  text?: string;
  children?: React.ReactNode;
  disabled?: boolean;
  hoverStyle?: IButtonStyleProps;
  activeStyle?: IButtonStyleProps;
  disabledStyle?: IButtonStyleProps;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

interface I$ButtonType extends IButtonStyleProps {
  styleType?: ButtonStyleType;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
  hoverStyle?: IButtonStyleProps;
  activeStyle?: IButtonStyleProps;
  disabledStyle?: IButtonStyleProps;
}

export type { IButtonProps, ButtonStyleType, IButtonStyleProps, ButtonStyle, I$ButtonType };
