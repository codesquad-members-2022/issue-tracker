import * as S from 'components/IssueList/styled.index';
import * as I from 'design/icons';
import Issues from './Issues';

function IssueList() {
  return (
    <S.IssueListWrap>
      <S.IssueListTop>
        <S.FilterBar>
          <S.Filter>
            필터
            <I.dropdownArrow />
          </S.Filter>
          <S.SearchBar placeholder="is:issue is:open" />
        </S.FilterBar>
        <S.IssueListTopRight>
          <S.Buttons>
            <S.LabelBtn>
              <I.tag />
              <S.BtnText>레이블</S.BtnText>
              <S.BtnContentCount>(3)</S.BtnContentCount>
            </S.LabelBtn>
            <S.MileStoneBtn>
              <I.milestone />
              <S.BtnText>마일스톤</S.BtnText>
              <S.BtnContentCount>(2)</S.BtnContentCount>
            </S.MileStoneBtn>
          </S.Buttons>
          <S.IssueOption>
            <I.plus />
            이슈 작성
          </S.IssueOption>
        </S.IssueListTopRight>
      </S.IssueListTop>
      <Issues />
    </S.IssueListWrap>
  );
}

export default IssueList;
