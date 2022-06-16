import * as S from 'components/IssueList/styled.index';
import Issues from './Issues';

function IssueList() {
  return (
    <S.IssueListWrap>
      <S.IssueListTop>
        <S.FilterBar>
          <S.Filter>필터</S.Filter>
          <S.SearchBar placeholder="is:issue is:open" />
        </S.FilterBar>
        <S.IssueListTopRight>
          <S.Buttons>
            <S.LabelBtn>레이블</S.LabelBtn>
            <S.MileStoneBtn>마일스톤</S.MileStoneBtn>
          </S.Buttons>
          <S.IssueOption>이슈 작성</S.IssueOption>
        </S.IssueListTopRight>
      </S.IssueListTop>
      <Issues />
    </S.IssueListWrap>
  );
}

export default IssueList;
