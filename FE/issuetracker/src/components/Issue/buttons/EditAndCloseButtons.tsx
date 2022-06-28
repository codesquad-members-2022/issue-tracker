import { useRecoilValue } from 'recoil';

import * as I from 'design/icons';
import * as S from 'components/Issue/styled/issueTitleButton';

import IssueTitleButton from 'components/Issue/buttons/IssueTitleButton';
import { issueState } from 'context/issue';

function EditAndCloseButtons({
  clickHandler1,
  clickHandler2,
}: {
  clickHandler1: () => void;
  clickHandler2: () => void;
}) {
  const issueData = useRecoilValue(issueState);
  return (
    <S.buttonWrapper>
      <IssueTitleButton
        buttonIcon={<I.edit />}
        buttonText="제목 편집"
        buttonState="default"
        handleButtonClick={handleEditButtonClick}
      />
      <IssueTitleButton
        buttonIcon={issueData.closed ? <I.alertCircle /> : <I.archive />}
        buttonText={issueData.closed ? '다시 열기' : '이슈 닫기'}
        buttonState="default"
        handleButtonClick={handleCloseButtonClick}
      />
    </S.buttonWrapper>
  );
}

export default EditAndCloseButtons;
