import Dropdown from '@/components/common/Dropdown';
import { Icon } from '@/components/common/Icon';
import { $IssueListFilterDropDowns } from '@/components/IssueList/IssueListFilterDropDowns/style';
import mockData from '@/components/IssueList/mockData';

const radioIcon = {
  off: <Icon iconType="radioOff" />,
  on: <Icon iconType="radioOn" />
};

const ISSUE_FILTERS_PROPS = [
  {
    indicatorName: '담당자',
    panelName: '담당자 필터',
    dataName: 'labelList'
  },
  {
    indicatorName: '레이블',
    panelName: '레이블 필터',
    dataName: 'labelList'
  },
  {
    indicatorName: '마일스톤',
    panelName: '마일스톤 필터',
    dataName: 'milestoneList'
  },
  {
    indicatorName: '작성자',
    panelName: '작성자 필터',
    dataName: 'milestoneList'
  }
];

export default function IssueListFilterDropDowns() {
  const getIssueFilterOptions = (dataName: 'labelList' | 'milestoneList') => {
    const options = mockData[dataName].map(
      ({ name, description }: { name: string; description: string }) => {
        return {
          children: name,
          radio: radioIcon,
          value: description
        };
      }
    );

    return options;
  };

  return (
    <$IssueListFilterDropDowns>
      {ISSUE_FILTERS_PROPS.map(({ dataName, ...props }, idx) => (
        <Dropdown key={idx} left="right" options={getIssueFilterOptions(dataName)} {...props} />
      ))}
    </$IssueListFilterDropDowns>
  );
}
