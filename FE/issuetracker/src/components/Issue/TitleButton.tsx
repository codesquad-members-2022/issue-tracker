import * as I from 'design/icons';
import * as S from 'components/Issue/styled.titleButton';

interface Props {
  buttonType: 'editButton' | 'archiveButton';
  buttonText: string;
}

function TitleButton({ buttonType, buttonText }: Props) {
  return (
    <S.issueButton>
      {buttonType === 'editButton' ? <I.edit /> : <I.archive />}
      <S.buttonText>{buttonText}</S.buttonText>
    </S.issueButton>
  );
}
export default TitleButton;
