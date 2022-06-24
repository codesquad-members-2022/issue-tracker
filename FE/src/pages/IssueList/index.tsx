import Button from '@/components/Button';
import FilterBar from '@/components/FilterBar';
import Header from '@/components/Header';
import { Icon } from '@/components/Icon';
import IssueList from '@/components/IssueList';
import Tabs from '@/components/Tabs';
import { listItem } from '@/components/Tabs/type';
import Layout from '@/layout';
import { $MenuWrapper, $ButtonWrapper } from '@/pages/IssueList/style';

const tabItems: listItem[] = [
  {
    name: '레이블',
    iconType: 'label',
    count: 100
  },
  {
    name: '마일스톤',
    iconType: 'milestone',
    count: 10
  }
];

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
