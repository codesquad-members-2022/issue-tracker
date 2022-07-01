import IssueListTabs from '@/components/IssueList/IssueListTabs';
import IssueListFilterDropDowns from '@/components/IssueList/IssueListFilterDropDowns';
import { $ListHeader, $IssueWrapper, $IssueList } from '@/components/IssueList/style';
import ListItem from './ListItem';
import { useFilterCondition } from '@/contexts/FilterCondition';
import { useIssueListData } from '@/hooks/useIssueListData';
import { IListItem } from './ListItem/type';

export default function List() {
  const filterCondition = useFilterCondition();
  const { status, data } = useIssueListData(filterCondition);
  return (
    <$IssueWrapper>
      <$ListHeader>
        <IssueListTabs />
        <IssueListFilterDropDowns />
      </$ListHeader>
      <$IssueList>
        {status === 'success' &&
          data.data.data
            // .reverse()
            .map((issueData: IListItem) => <ListItem key={issueData.id} {...issueData} />)}
      </$IssueList>
    </$IssueWrapper>
  );
}
