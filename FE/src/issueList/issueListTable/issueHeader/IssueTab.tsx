import Icon from '@icon';
import { GREYSCALE } from '@constants';
import styled from 'styled-components';
import { useQuery } from 'react-query';
import { fetchDataUseAxios, getRandomKey } from '@/utils';
import DropDownPanel from '@components/dropDown/DropDownPanel';

type IssueTabProps = {
  tabId: string;
  tabName: string;
};

const getDropDownItems = (tabId, fetchedItems) => {
  const items = fetchedItems.map((item) => {
    const {
      id,
      name,
      backgroundColor,
      memberId,
      memberName,
      profile,
      title,
      authorId,
      authorName
    } = item;

    switch (tabId) {
      case 'labels':
        return { id, label: name, color: backgroundColor };
      case 'members':
        return { id: memberId, label: memberName, img: profile };
      case 'milestones':
        return { id, label: title };
      case 'authors':
        return { id: authorId, label: authorName };
      default:
        return { id: getRandomKey(), label: '옵션 없음' };
    }
  });

  return items;
};

function IssueTab({ tabId, tabName }: IssueTabProps) {
  const { isLoading, data, error } = useQuery(`${tabId}Options`, () =>
    fetchDataUseAxios(`api/issue/${tabId}`)
  );

  if (isLoading) return <div>loading</div>;
  if (error) return <div>error</div>;
  if (!data) return <div>no data</div>;

  const DropDownData = {
    items: getDropDownItems(tabId, data),
    showCheckBox: true,
    filterName: `${tabName} 필터`
  };

  return (
    <IssueTabBox>
      <details>
        <TabSummary>
          <TabName>{tabName}</TabName>
          <Icon iconName="chevronDown" stroke={GREYSCALE.LABEL} />
        </TabSummary>
        <DropDownBox>
          <DropDownPanel {...DropDownData} />
        </DropDownBox>
      </details>
    </IssueTabBox>
  );
}

const IssueTabBox = styled.li`
  position: relative;
`;

const TabSummary = styled.summary`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'flex-start', 'center')};
  gap: 8px;
  cursor: pointer;
`;

const TabName = styled.p`
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL}
  color: ${GREYSCALE.LABEL};
`;

const DropDownBox = styled.div`
  position: absolute;
  top: 32px;
  right: 0;
`;

export default IssueTab;
