import Icon from '@/assets/icons/Icon';
import { GREYSCALE } from '@/constants';
import React from 'react';
import styled from 'styled-components';

type IssueTabProps = {
  tabName: string;
};

function IssueTab({ tabName }: IssueTabProps) {
  return (
    <IssueTabBox>
      <TabName>{tabName}</TabName>
      <Icon
        iconName="chevronDown"
        width={10}
        height={6}
        stroke={GREYSCALE.LABEL}
      />
    </IssueTabBox>
  );
}

const IssueTabBox = styled.li`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start', 'center')};
  gap: 8px;
`;

const TabName = styled.p`
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
  color: ${GREYSCALE.LABEL};
`;

export default IssueTab;
