import Button from '@/components/common/Button';
import FilterBar from '@/components/IssueList/FilterBar';
import Header from '@/components/Header';
import { Icon } from '@/components/common/Icon';
import IssueList from '@/components/IssueList';
import Tabs from '@/components/common/Tabs';
import { listItem } from '@/components/common/Tabs/type';
import Layout from '@/layout';
import { $MenuWrapper, $ButtonWrapper } from '@/pages/IssueList/style';
import { FilterConditionProvider } from '@/contexts/FilterCondition';
import { FILTER_BAR_OPTIONS } from './filterBarOptionData';
import { IFilterBarProps } from '@/components/IssueList/FilterBar/type';
import { useLabelCountData } from '@/hooks/useLabelListData';
import { useMilestoneCountData } from '@/hooks/useMilestoneListData';

const filterBarProps: IFilterBarProps = {
  indicatorName: '필터',
  panelName: '이슈 필터',
  options: FILTER_BAR_OPTIONS,
  initialValue: 'opened',
  inputStyleType: 'small',
  placeholder: 'is:issue is:open',
  name: 'issueFilter'
};

export default function IssueListPage() {
  const { status: labelDataStatus, data: labelCount } = useLabelCountData();
  const { status: milestoneDataStatus, data: milestoneCount } = useMilestoneCountData();

  const tabItems: listItem[] = [
    {
      name: '레이블',
      iconType: 'label',
      count: labelCount?.data.total_count
    },
    {
      name: '마일스톤',
      iconType: 'milestone',
      count: milestoneCount?.data.total_count
    }
  ];

  return (
    <Layout header={<Header />}>
      <FilterConditionProvider>
        <$MenuWrapper>
          <FilterBar {...filterBarProps} />
          <$ButtonWrapper>
            <Tabs list={tabItems} />
            <Button styleType="smallStandard">
              <Icon iconType="plus" />
              이슈 작성
            </Button>
          </$ButtonWrapper>
        </$MenuWrapper>
        <IssueList />
      </FilterConditionProvider>
    </Layout>
  );
}
