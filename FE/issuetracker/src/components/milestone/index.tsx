import { useState } from 'react';
import * as I from 'design/icons';
import * as S from 'components/milestone/styled/styled.index';
import * as L from 'components/LabelPage/styled/styled.labelList';
import LabelAndMileStoneBtns from 'components/common/LabelAndMileStoneBtns';
import MileStone from 'components/milestone/MileStone';
import NewMileStone from 'components/milestone/NewMileStone';
import { mileStoneData } from 'context/milestone';
import { keyMaker } from 'utils/util';

function MileStonePage() {
  const [isAddButtonClicked, setAddButtonClick] = useState(false);
  const handleButtonClick = () => {
    setAddButtonClick(!isAddButtonClicked);
  };

  return (
    <S.MileStonePageWrapper>
      <LabelAndMileStoneBtns
        isAddButtonClicked={isAddButtonClicked}
        handleAddButtonClick={handleButtonClick}
        handleCloseButtonClick={handleButtonClick}
      />
      {isAddButtonClicked && <NewMileStone />}
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
        {mileStoneData.map((mileStone, idx) => {
          const key: string = keyMaker();
          return <MileStone key={key} idx={idx} mileStone={mileStone} />;
        })}
      </L.labelListLayout>
    </S.MileStonePageWrapper>
  );
}

export default MileStonePage;
