import React from 'react';
import { icons } from '@/assets/icons/svgs';

type IconProps = {
  iconName: keyof typeof icons;
  width?: number;
  height?: number;
};

function Icon({ iconName, width = 16, height = 16 }: IconProps) {
  const IconSvg = icons[iconName];
  return <IconSvg width={width} height={height} />;
}

export default Icon;
