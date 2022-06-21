import * as S from 'components/milestone/styled/styled.milestone';
import * as I from 'design/icons';

type MileStoneType = {
  idx: number;
  title: string;
};

function MileStone({ idx, title }: MileStoneType) {
  return (
    <S.MileStoneWrap idx={idx}>
      <S.MileStoneTop>
        <S.MileStoneTopLeft>
          <S.MileStoneIconFigure>
            <I.milestone />
          </S.MileStoneIconFigure>
          <S.Title>{title}</S.Title>
          <S.DueDate>
            <I.calendar />
            완료일 일정
          </S.DueDate>
        </S.MileStoneTopLeft>
      </S.MileStoneTop>
    </S.MileStoneWrap>
  );
}

export default MileStone;
