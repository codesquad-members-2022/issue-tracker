import { FlattenInterpolation, ThemeProps, StyledComponent } from 'styled-components';

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
  gap?: string;
  styleType?: ButtonStyleType;
  children?: React.ReactNode;
  disabled?: boolean;
  hoverStyle?: IButtonStyleProps;
  activeStyle?: IButtonStyleProps;
  disabledStyle?: IButtonStyleProps;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

interface I$ButtonType extends IButtonStyleProps {
  gap?: string;
  styleType?: ButtonStyleType;
  hoverStyle?: IButtonStyleProps;
  activeStyle?: IButtonStyleProps;
  disabledStyle?: IButtonStyleProps;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

export type { IButtonProps, ButtonStyleType, IButtonStyleProps, ButtonStyle, I$ButtonType };
