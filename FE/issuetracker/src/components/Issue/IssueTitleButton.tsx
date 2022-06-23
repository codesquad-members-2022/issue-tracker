import * as S from 'components/Issue/styled/issueTitleButton';

interface Props {
  buttonIcon: React.ReactNode;
  buttonText: string;
}

function IssueTitleButton({ buttonIcon, buttonText }: Props) {
  return (
    <S.issueButton>
      {buttonIcon}
      <S.buttonText>{buttonText}</S.buttonText>
    </S.issueButton>
  );
}
export default IssueTitleButton;
