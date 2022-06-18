import { useState } from 'react';
import * as I from 'design/icons';
import * as S from 'components/LabelPage/styled.labelTop';
import NewLabelList from 'components/LabelPage/NewLabelList';

interface Props {
  activeLabel: boolean;
}

const initialLabel = {
  title: '레이블 이름',
  color: '',
  description: '레이블에 대한 설명',
};

function LabelTop({ activeLabel }: Props) {
  const [isClicked, setIsClicked] = useState(false);
  return (
    <>
      <S.labelTop>
        <S.tabBar>
          <S.leftBar to="/label" activeLabel={activeLabel}>
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
        <S.addButton onClick={() => setIsClicked(true)}>
          <I.plus />
          <S.addButtonText>추가</S.addButtonText>
        </S.addButton>
      </S.labelTop>
      {isClicked && <NewLabelList label={initialLabel} isNewLabel />}
    </>
  );
}

export default LabelTop;
