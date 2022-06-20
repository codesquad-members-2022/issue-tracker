import * as I from 'design/icons';
import * as S from 'components/milestone/styled/styled.index';
import LabelAndMileStoneBtns from 'components/common/LabelAndMileStoneBtns';
import * as L from 'components/LabelPage/styled.labelList';

function MileStonePage() {
  return (
    <S.MileStoneWrapper>
      <LabelAndMileStoneBtns />
      <L.labelListLayout>
        <L.labelListTop>
          <S.MileStoneStatus>
            <S.OpenedMileStone>
              <I.milestone />
              열린 마일스톤(2)
            </S.OpenedMileStone>
            <S.ClosedMileStone>
              <I.archive />
              닫힌 마일스톤(0)
            </S.ClosedMileStone>
          </S.MileStoneStatus>
        </L.labelListTop>
      </L.labelListLayout>
    </S.MileStoneWrapper>
  );
}

export default MileStonePage;
