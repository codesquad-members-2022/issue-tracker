import * as S from './Issues.styled';

function MoveBtn() {
  return (
    <S.AddForm>
      <S.LabelMilStoneContainer>
        <S.LabelMilStoneWrapper to="/Labels">
          <img src="icons/labelIcon.svg" alt="" />
          <p>레이블(2)</p>
        </S.LabelMilStoneWrapper>
        <S.LabelMilStoneWrapper
          to="/Milestones"
          style={{ borderRight: 'none' }}
        >
          <img src="icons/milstoneIcon.svg" alt="" />
          <p>마일스톤(3)</p>
        </S.LabelMilStoneWrapper>
      </S.LabelMilStoneContainer>
      <S.AddIssueBtn to="/NewIssue" size="sm">
        <img src="icons/plusIcon.svg" alt="" />
        <p>이슈등록</p>
      </S.AddIssueBtn>
    </S.AddForm>
  );
}

export default MoveBtn;
