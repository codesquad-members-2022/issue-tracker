import React from 'react';

import styled from 'styled-components';
import { colors } from 'styles/theme';
import Button from 'components/Atoms/Button';
import { TabTypes } from 'components/Atoms/Tab';
import Tabs from 'components/Molecules/Tabs';

import { SUB_NAV_BUTTON_STYLES } from 'helpers/constants/ButtonStyles';

const StyledDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

interface SubNavTypes {
  type?: 'ADD' | 'CLOSE';
  labelCount: number;
  milestoneCount: number;
  HandleOnClick: (e: React.MouseEvent<HTMLButtonElement>) => void;
}

const SubNav = ({ labelCount, milestoneCount, HandleOnClick, type = 'ADD' }: SubNavTypes) => {
  const tabInfos: TabTypes[] = [
    {
      border: 'LEFT',
      count: labelCount,
      iconInfo: {
        icon: 'Tag',
      },
      label: '레이블',
      link: '/labels',
      tabStyle: 'FILL',
    },
    {
      border: 'RIGHT',
      count: milestoneCount,
      iconInfo: {
        fill: colors.titleActive,
        icon: 'Milestone',
      },
      label: '마일스톤',
      link: '/milestones',
      tabStyle: 'FILL',
    },
  ];

  const buttonStyles = type === 'CLOSE' ? SUB_NAV_BUTTON_STYLES.close : SUB_NAV_BUTTON_STYLES.add;

  return (
    <StyledDiv>
      <Tabs tabInfos={tabInfos} />
      <Button {...buttonStyles} HandleOnClick={HandleOnClick} />
    </StyledDiv>
  );
};

export default SubNav;
