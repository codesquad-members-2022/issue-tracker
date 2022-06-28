import * as S from 'components/Issue/styled/issueTitleButton';

function IssueTitleButton({
  buttonIcon,
  buttonText,
  buttonState,
  clickHandler,
}: {
  buttonIcon: React.ReactNode;
  buttonText: string;
  buttonState: string;
  clickHandler: () => void;
}) {
  return (
    <S.issueButton buttonState={buttonState} onClick={handleButtonClick}>
      {buttonIcon}
      <S.buttonText>{buttonText}</S.buttonText>
    </S.issueButton>
  );
}
export default IssueTitleButton;
