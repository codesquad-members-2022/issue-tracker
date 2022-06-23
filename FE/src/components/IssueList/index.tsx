import ListHeader from '@/components/IssueList/ListHeader';
import { $IssueWrapper, $IssueList } from '@/components/IssueList/style';
import ListItem from './ListItem';
import mockData from './mockData';

export default function List() {
  return (
    <$IssueWrapper>
      <ListHeader />
      <$IssueList>
        {mockData && mockData.map(issueData => <ListItem key={issueData.number} {...issueData} />)}
      </$IssueList>
    </$IssueWrapper>
  );
}
