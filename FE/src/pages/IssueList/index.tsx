import Button from '@/components/common/Button';
import FilterBar from '@/components/IssueList/FilterBar';
import Header from '@/components/Header';
import { Icon } from '@/components/common/Icon';
import IssueList from '@/components/IssueList';
import Tabs from '@/components/common/Tabs';
import { listItem } from '@/components/common/Tabs/type';
import Layout from '@/layout';
import { $MenuWrapper, $ButtonWrapper } from '@/pages/IssueList/style';
import { useLabelCountData } from '@/hooks/useLabelListData';
import { useMilestoneCountData } from '@/hooks/useMilestoneListData';

const radioIcon = {
  off: <Icon iconType="radioOff" />,
  on: <Icon iconType="radioOn" />
};

const filterBarProps = {
  indicatorName: '필터',
  panelName: '이슈 필터',
  options: [
    {
      children: '열린 이슈',
      radio: radioIcon,
      value: 'opened'
    },
    {
      children: '내가 작성한 이슈',
      radio: radioIcon,
      value: 'written'
    },
    {
      children: '나에게 할당된 이슈',
      radio: radioIcon,
      value: 'assigned'
    },
    {
      children: '내가 댓글을 남긴 이슈',
      radio: radioIcon,
      value: 'comments'
    },
    {
      children: '닫힌 이슈',
      radio: radioIcon,
      value: 'closed'
    }
  ],
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
    </Layout>
  );
}
