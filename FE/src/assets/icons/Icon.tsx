import React from 'react';
import { icons } from '@/assets/icons/svgs';

const Icon = ({ iconName, width = 16, height = 16 }) => {
  const IconSvg = icons[iconName];
  return <IconSvg width={width} height={height} />;
};

export default Icon;
