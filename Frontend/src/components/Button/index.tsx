import React from 'react';

import Icons, { IconType } from '@/components/Icons';

import * as S from './style';

export type ButtonStyleType =
  | 'large'
  | 'smallStandard'
  | 'mediumStandard'
  | 'smallSecondary'
  | 'mediumText'
  | 'smallText'
  | 'sort'
  | 'largeLogin';

export interface ButtonCustomStyleProps {
  width?: string;
  height?: string;
  color?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
  fontSize?: string;
  fontWeight?: string;
}

export interface ButtonProps extends ButtonCustomStyleProps {
  buttonStyle: ButtonStyleType;
  hasIcon: boolean;
  iconType?: IconType;
  contents?: string;
  children?: React.ReactNode;
  disabled?: boolean;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

export interface StyledButtonProps extends ButtonCustomStyleProps {
  buttonStyle: ButtonStyleType;
}

export default function Button({
  buttonStyle,
  contents,
  children,
  iconType = 'plus',
  hasIcon,
  ...props
}: ButtonProps) {
  return (
    <S.Button buttonStyle={buttonStyle} {...props}>
      {hasIcon && <Icons type={iconType} />}
      <S.Contents>{contents || children}</S.Contents>
    </S.Button>
  );
}
