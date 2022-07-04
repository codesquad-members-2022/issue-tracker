import { GREYSCALE } from '@constants';
import Icon from '@icon';
import { IssueTabBox, TabSummary, TabName, DropDownBox } from './IssueTab';
import DropDownPanel from '@/common/components/dropDown/DropDownPanel';

type StatusIssueTabProps = {
  tabName: string;
};

function StatusIssueTab({ tabName }: StatusIssueTabProps) {
  const DropDownData = {
    items: [
      { id: 1, label: '선택한 이슈 열기' },
      { id: 2, label: '선택한 이슈 닫기' }
    ],
    showCheckBox: false,
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

export default StatusIssueTab;
