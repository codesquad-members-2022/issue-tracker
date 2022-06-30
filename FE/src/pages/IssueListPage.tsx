import FilterBar from '@/issueList/FilterBar';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import LinkTabs from '@/issueList/LinkTabs';
import ButtonSmallStandard from '@/common/components/buttons/ButtonSmallStandard';
import IssueList from '@/issueList/issueListTable/IssueList';
import IssueListProvider from '@/issueList/stores/IssueListProvider';

function IssueListPage() {
  return (
    <>
      <Wrap>
        <FilterBar />
        <LinkTabs />
        <Link to="/addIssue">
          <ButtonSmallStandard isDisabled={false} label="이슈 작성" />
        </Link>
      </Wrap>
      <IssueListProvider>
        <IssueList />
      </IssueListProvider>
    </>
  );
}

const Wrap = styled.div`
  width: 1280px;
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'space-between')}
  margin-bottom: 24px;

  & > div:nth-child(2) {
    margin-left: auto;
    margin-right: 16px;
  }
`;

export default IssueListPage;
