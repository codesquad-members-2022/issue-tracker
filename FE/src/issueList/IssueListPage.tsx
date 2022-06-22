import EmptyIssueItem from './EmptyIssueItem';
import IssueHeader from './IssueHeader';
import IssueItem from './IssueItem';
import FilterBar from '@/common/FilterBar';
import Taps from '@/common/Taps';
import ButtonSmallStandard from '@/common/ButtonSmallStandard';
import styled from 'styled-components';

function IssueListPage() {
  return (
    <>
      <Wrap>
        <FilterBar />
        <Taps />
        <ButtonSmallStandard
          isDisabled={false}
          label="이슈 작성"
          onClick={() => console.log('click 이슈작성버튼')}
        />
      </Wrap>
      <div>
        <IssueHeader />
        <div>
          <IssueItem />
          <IssueItem isLast />
        </div>
      </div>
    </>
  );
}

const Wrap = styled.div`
  width: 1280px;
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'space-between')}
  margin-bottom: 24px;

  div:nth-child(2) {
    margin-left: auto;
    margin-right: 16px;
  }
`;

export default IssueListPage;
