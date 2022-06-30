import StyledLabel from 'components/Atoms/Label/index.styles';
import React from 'react';
import { colors } from 'styles/theme';

export interface LabelTypes {
  id?: number;
  icon?: React.ReactNode;
  title: string;
  labelStyle?: 'STANDARD' | 'LINE';
  titleColor: 'white' | 'black';
  backgroundColor: string;
  description?: string;
}

const DEFAULT_COLORS = colors.primary.blue;
const DEFAULT_TITLE_COLORS = 'white';
const Label = ({
  labelStyle = 'STANDARD',
  titleColor = DEFAULT_TITLE_COLORS,
  backgroundColor = DEFAULT_COLORS,
  ...props
}: LabelTypes) => {
  const { title, icon } = props;

  return (
    <StyledLabel labelStyle={labelStyle} titleColor={titleColor} backgroundColor={backgroundColor}>
      {icon}
      <span>{title}</span>
    </StyledLabel>
  );
};

export default Label;
