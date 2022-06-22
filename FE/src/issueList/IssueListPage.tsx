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
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row')}
  width:1280px;
`;

export default IssueListPage;
