import styled from 'styled-components';
import { GREYSCALE } from '@/constants';
import Icon from '@/assets/icons/Icon';

type TabProps = {
  isActive: boolean;
  iconName: 'tag' | 'milestone';
  title: string;
  count: number;
};

const getTabIcon = (iconName: 'tag' | 'milestone', iconColor: string) => {
  switch (iconName) {
    case 'tag':
      return <Icon iconName="tag" stroke={iconColor} />;
    case 'milestone':
      return <Icon iconName="milestone" fill={iconColor} />;
    default:
  }
};

function Tab({ isActive, iconName, title, count }: TabProps) {
  const Box = isActive ? TabBoxActive : TabBoxInitial;
  const iconColor = isActive ? GREYSCALE.BODY : GREYSCALE.LABEL;

  return (
    <Box>
      {getTabIcon(iconName, iconColor)}
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

const TabBox = styled.button`
  width: 160px;
  height: 40px;
  color: ${GREYSCALE.LABEL};
  background-color: ${GREYSCALE.BACKGROUND};

  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'center', 'center')}
  gap: 8px;
`;

const TabBoxInitial = styled(TabBox)`
  &:hover {
    background-color: ${GREYSCALE.INPUT_BACKGROUND};
  }
`;

const TabBoxActive = styled(TabBox)`
  background-color: ${GREYSCALE.LINE};
  color: ${GREYSCALE.BODY};
`;

export default Tab;
