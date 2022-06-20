import React from 'react';
import { icons } from '@/assets/icons/svgs';

type IconProps = {
  iconName: keyof typeof icons;
  width?: number;
  height?: number;
  stroke?: string;
  fill?: string;
};

function Icon({
  iconName,
  width = 16,
  height = 16,
  stroke = 'none',
  fill = 'none',
}: IconProps) {
  const IconSvg = icons[iconName];
  return <IconSvg width={width} height={height} stroke={stroke} fill={fill} />;
}

export default Icon;
