import * as S from 'components/Issue/styled/issueTitleButton';

function IssueTitleButton({
  buttonIcon,
  buttonText,
  buttonState,
  handleButtonClick,
}: {
  buttonIcon: React.ReactNode;
  buttonText: string;
  buttonState: string;
  handleButtonClick: () => void;
}) {
  return (
    <S.issueButton buttonState={buttonState} onClick={handleButtonClick}>
      {buttonIcon}
      <S.buttonText>{buttonText}</S.buttonText>
    </S.issueButton>
  );
}
export default IssueTitleButton;
