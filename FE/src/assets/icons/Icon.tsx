import React from 'react';
import * as icons from './svgs';

const Icon = ({ iconName, width = 16, height = 16 }) => {
  const IconSvg = icons[iconName];
  return <IconSvg width={width} height={height} />;
};

export default Icon;
