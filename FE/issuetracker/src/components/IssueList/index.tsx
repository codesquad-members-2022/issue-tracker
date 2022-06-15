import * as S from 'components/IssueList/styled.index';

function IssueList() {
  return (
    <S.IssueListWrap>
      <S.IssueListTop>
        <S.FilterBar>
          <S.Filter />
          <S.SearchBar />
        </S.FilterBar>
        <S.IssueListTopRight>
          <S.Buttons>
            <S.LabelButton>Label</S.LabelButton>
          </S.Buttons>
        </S.IssueListTopRight>
      </S.IssueListTop>
    </S.IssueListWrap>
  );
}

export default IssueList;
