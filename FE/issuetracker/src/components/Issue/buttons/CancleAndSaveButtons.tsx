import * as I from 'design/icons';
import * as S from 'components/Issue/styled/issueTitleButton';
import IssueTitleButton from 'components/Issue/buttons/IssueTitleButton';

function CancleAndSaveButtons({
  clickHandler1,
  clickHandler2,
}: {
  clickHandler1: () => void;
  clickHandler2: () => void;
}) {
  return (
    <S.buttonWrapper>
      <IssueTitleButton
        buttonIcon={<I.cross />}
        buttonText="편집 취소"
        buttonState="default"
        handleButtonClick={handleCancleButtonClick}
      />
      <IssueTitleButton
        buttonIcon={<I.edit />}
        buttonText="편집 완료"
        buttonState="active"
        handleButtonClick={handleSaveButtonClick}
      />
    </S.buttonWrapper>
  );
}

export default CancleAndSaveButtons;
