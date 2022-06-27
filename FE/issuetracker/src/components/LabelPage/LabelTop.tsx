import { useState } from 'react';
<<<<<<< HEAD
import LabelAndMileStoneBtns from 'components/common/LabelAndMileStoneBtns';
import NewLabelList from 'components/LabelPage/NewLabelList';

function LabelTop() {
=======
import * as I from 'design/icons';
import * as S from 'components/LabelPage/styled/styled.labelTop';
import NewLabelList from 'components/LabelPage/NewLabelList';

interface Props {
  $activeLabel: boolean;
}

function LabelTop({ $activeLabel }: Props) {
>>>>>>> origin/29-featfe-사이드-바-상태-구현
  const [isClicked, setIsClicked] = useState(false);
  const isNewLabel = true;
  const showLabel = () => {
    setIsClicked(!isClicked);
  };
  return (
    <>
<<<<<<< HEAD
      <LabelAndMileStoneBtns
        isAddButtonClicked={isClicked}
        handleAddButtonClick={showLabel}
        handleCloseButtonClick={showLabel}
      />
=======
      <S.labelTop>
        <S.tabBar>
          <S.leftBar to="/label" $activeLabel={$activeLabel}>
            <I.tag />
            <S.LinkText>레이블</S.LinkText>
            <S.numberText>(3)</S.numberText>
          </S.leftBar>
          <S.rightBar to="/milestone">
            <I.milestone />
            <S.LinkText>마일스톤</S.LinkText>
            <S.numberText>(2)</S.numberText>
          </S.rightBar>
        </S.tabBar>
        <S.addButton
          onClick={() => {
            showLabel();
          }}
        >
          <I.plus />
          <S.addButtonText>추가</S.addButtonText>
        </S.addButton>
      </S.labelTop>
>>>>>>> origin/29-featfe-사이드-바-상태-구현
      {isClicked && <NewLabelList isNewLabel={isNewLabel} />}
    </>
  );
}

export default LabelTop;
