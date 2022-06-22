import React from 'react';
import styled from 'styled-components';
import { GREYSCALE } from '@/constants';
import Icon from '@/assets/icons/Icon';

type TapProps = {
  isActive: boolean;
  iconName: 'tag' | 'milestone';
  title: string;
  count: number;
};

const getTapIcon = (iconName: 'tag' | 'milestone', iconColor: string) => {
  switch (iconName) {
    case 'tag':
      return <Icon iconName="tag" stroke={iconColor} />;
    case 'milestone':
      return <Icon iconName="milestone" fill={iconColor} />;
    default:
  }
};

function Tap({ isActive, iconName, title, count }: TapProps) {
  const Box = isActive ? TapBoxActive : TapBoxInitial;
  const iconColor = isActive ? GREYSCALE.BODY : GREYSCALE.LABEL;

  return (
    <Box>
      {getTapIcon(iconName, iconColor)}
      <Title>{title}</Title>
      <Count>({count})</Count>
    </Box>
  );
}

const Title = styled.p`
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
`;

const Count = styled.p`
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_SMALL}
`;

const TapBox = styled.button`
  width: 160px;
  height: 40px;
  color: ${GREYSCALE.LABEL};
  background-color: ${GREYSCALE.BACKGROUND};

  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'center', 'center')}
  gap: 8px;
`;

const TapBoxInitial = styled(TapBox)`
  &:hover {
    background-color: ${GREYSCALE.INPUT_BACKGROUND};
  }
`;

const TapBoxActive = styled(TapBox)`
  background-color: ${GREYSCALE.LINE};
  color: ${GREYSCALE.BODY};
`;

export default Tap;
