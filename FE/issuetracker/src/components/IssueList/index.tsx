import * as S from 'components/IssueList/styled.index';
import * as I from 'design/icons';
import { useState } from 'react';
import Issues from './Issues';

function IssueList() {
  const [isClicked, setIsClicked] = useState(false);

  function toggleDropdown() {
    setIsClicked(!isClicked);
  }
  return (
    <S.IssueListWrap>
      <S.IssueListTop>
        <S.FilterBar>
          <S.Filter
            onClick={() => {
              toggleDropdown();
            }}
          >
            필터
            <I.dropdownArrow />
            <S.Dropdown isClicked={isClicked}>
              <S.DropdownTitle>이슈 필터</S.DropdownTitle>
              <S.DropdownContent>
                열린 이슈
                <I.offCheckCircle />
              </S.DropdownContent>
              <S.DropdownContent>
                내가 작성한 이슈
                <I.offCheckCircle />
              </S.DropdownContent>
              <S.DropdownContent>
                나에게 할당된 이슈
                <I.offCheckCircle />
              </S.DropdownContent>
              <S.DropdownContent>
                내가 댓글을 남긴 이슈
                <I.offCheckCircle />
              </S.DropdownContent>
              <S.DropdownContent>
                닫힌 이슈
                <I.offCheckCircle />
              </S.DropdownContent>
            </S.Dropdown>
          </S.Filter>
          <S.SearchBar placeholder="is:issue is:open" />
        </S.FilterBar>
        <S.IssueListTopRight>
          <S.Buttons>
            <S.LabelBtn to="/label">
              <I.tag />
              <S.BtnText>레이블</S.BtnText>
              <S.BtnContentCount>(3)</S.BtnContentCount>
            </S.LabelBtn>
            <S.MileStoneBtn to="/milestone">
              <I.milestone />
              <S.BtnText>마일스톤</S.BtnText>
              <S.BtnContentCount>(2)</S.BtnContentCount>
            </S.MileStoneBtn>
          </S.Buttons>
          <S.IssueOption to="/newIssue">
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
