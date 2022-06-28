import IssueListTabs from '@/components/IssueList/IssueListTabs';
import IssueListFilterDropDowns from '@/components/IssueList/IssueListFilterDropDowns';
import { $ListHeader, $IssueWrapper, $IssueList } from '@/components/IssueList/style';
import ListItem from './ListItem';
import mockData from './mockData';

export default function List() {
  return (
    <$IssueWrapper>
      <$ListHeader>
        <IssueListTabs />
        <IssueListFilterDropDowns />
      </$ListHeader>
      <$IssueList>
        {mockData &&
          mockData.issueList.map(issueData => <ListItem key={issueData.number} {...issueData} />)}
      </$IssueList>
    </$IssueWrapper>
  );
}
