import * as S from 'components/common/Sidebar/styled';
import { ProgressBar } from 'components/common/Common';
import { MileStoneType } from 'data';

function MileStoneContents({ mileStone }: { mileStone: MileStoneType }) {
  const percent = (mileStone.closedIssue / (mileStone.openedIssue + mileStone.closedIssue)) * 100;
  const mileStoneContent =
    mileStone.title !== '' ? (
      <>
        <ProgressBar size="small" percent={percent} />
        {mileStone.title}
      </>
    ) : (
      ''
    );
  return <S.Status>{mileStoneContent}</S.Status>;
}

export default MileStoneContents;
