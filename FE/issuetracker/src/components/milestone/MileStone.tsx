/* eslint-disable @typescript-eslint/no-empty-function */
import { useState } from 'react';
import * as I from 'design/icons';
import * as S from 'components/milestone/styled/styled.milestone';
import { ProgressBar } from 'components/common/Common';
import EditAndDeleteBtn from 'components/common/LabelAndMileStoneBtns/EditAndDeleteBtn';
import NewMileStone from 'components/milestone/NewMileStone';
import { mileStoneType, mileStoneData } from 'context/milestone';
import { calculatePercent, getDataByKey } from 'utils/util';

interface Props {
  idx: number;
  mileStone: mileStoneType;
}

function MileStone({
  idx,
  mileStone: { id, title, dueDate, description, progress, openedIssue, closedIssue },
}: Props) {
  const [isEditButtonClick, setEditButtonClick] = useState(false);
  const [elementId, setElementId] = useState(0);
  const percent = calculatePercent(progress);

  const handleEditButtonClick = (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    setEditButtonClick(true);
    const elementIdNumber = Number(e.currentTarget.id);
    setElementId(elementIdNumber);
  };

  const selectedMileStoneData = getDataByKey(mileStoneData, 'id', elementId);

  return (
    <>
      <S.MileStoneWrap idx={idx}>
        <S.MileStoneTop>
          <S.MileStoneTopLeft>
            <S.MileStoneIconFigure>
              <I.milestone />
            </S.MileStoneIconFigure>
            <S.Title>{title}</S.Title>
            <S.DueDate>
              <I.calendar />
              {dueDate}
            </S.DueDate>
          </S.MileStoneTopLeft>
          <S.MileStoneTopRight>
            <S.CloseMileStone>
              <I.milestone />
              닫기
            </S.CloseMileStone>
            <EditAndDeleteBtn
              elementId={id.toString()}
              handleEditButtonClick={handleEditButtonClick}
              handleDeleteButtonClick={() => {}}
            />
          </S.MileStoneTopRight>
        </S.MileStoneTop>
        <S.MileStoneBottom>
          <S.Discription>{description}</S.Discription>
          <S.MileStoneProgressBar>
            <ProgressBar percent={percent} />
            <S.ProgressStatus>
              <S.CompleteRate>{percent}%</S.CompleteRate>
              <S.IssueStatus>
                열린 이슈 {openedIssue} 닫힌 이슈 {closedIssue}
              </S.IssueStatus>
            </S.ProgressStatus>
          </S.MileStoneProgressBar>
        </S.MileStoneBottom>
      </S.MileStoneWrap>
      {isEditButtonClick && <NewMileStone mileStoneData={selectedMileStoneData} />}
    </>
  );
}

export default MileStone;
