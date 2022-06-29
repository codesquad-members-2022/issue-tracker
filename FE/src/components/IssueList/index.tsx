import IssueListTabs from '@/components/IssueList/IssueListTabs';
import IssueListFilterDropDowns from '@/components/IssueList/IssueListFilterDropDowns';
import { $ListHeader, $IssueWrapper, $IssueList } from '@/components/IssueList/style';
import ListItem from './ListItem';
import mockData from './mockData';
import { useFilterCondition } from '@/contexts/FilterCondition';
import { useIssueListData } from '@/hooks/useIssueListData';
import { IssueStatusType } from '@/types/common';
import { APIIssueStatusType } from '@/api/type';
import { IListItem } from './ListItem/type';

const convertIssueTypeToAPIType = (
  issueStatus?: IssueStatusType
): APIIssueStatusType | undefined => {
  if (!issueStatus) return undefined;
  return issueStatus === 'OPEN' ? 'open' : 'close';
};

export default function List() {
  const { status: issueStatus } = useFilterCondition();
  const { status, data } = useIssueListData(convertIssueTypeToAPIType(issueStatus));
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
