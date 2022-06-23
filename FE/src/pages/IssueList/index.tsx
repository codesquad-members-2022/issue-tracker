import Button from '@/components/Button';
import FilterBar from '@/components/FilterBar';
import Header from '@/components/Header';
import { Icon } from '@/components/Icon';
import IssueList from '@/components/IssueList';
import Tabs from '@/components/Tabs';
import { listItem } from '@/components/Tabs/type';
import Layout from '@/layout';
import { $Contents, $MenuWrapper, $ButtonWrapper } from '@/pages/IssueList/style';

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

export default function IssueListPage() {
  return (
    <Layout header={<Header />}>
      <$Contents>
        <$MenuWrapper>
          <FilterBar />
          <$ButtonWrapper>
            <Tabs list={tabItems} />
            <Button styleType="smallStandard">
              <Icon iconType="plus" />
              이슈 작성
            </Button>
          </$ButtonWrapper>
        </$MenuWrapper>
        <IssueList />
      </$Contents>
    </Layout>
  );
}
