import FilterBar from 'common/Filter/FilterBar';
import * as S from './Issues.styled';
import { author, label, mileStone, writer } from './Constants';

function IssueHeader() {
  return (
    <S.IssueHeader>
      <S.Issue>
        <S.Check type="checkbox" />
        <S.OpenedIssue>
          <img src="icons/openIssueIcon.svg" alt="" />
          <p>열린 이슈(2)</p>
        </S.OpenedIssue>
        <S.ClosedIssue>
          <img src="icons/closeIssueIcon.svg" alt="" />
          <p>닫힌 이슈(3)</p>
        </S.ClosedIssue>
      </S.Issue>
      <S.Table>
        <FilterBar filterTitle="담당자" filterList={author} />
        <FilterBar filterTitle="레이블" filterList={label} />
        <FilterBar filterTitle="마일스톤" filterList={mileStone} />
        <FilterBar filterTitle="작성자" filterList={writer} />
      </S.Table>
    </S.IssueHeader>
  );
}

export default IssueHeader;
