import * as I from 'design/icons';
import * as S from 'components/common/SaveButton/styled.index';

interface Props {
  buttonText: string;
  margin: string;
  clickHandler: () => void;
}

function SaveButton({ buttonText, margin, clickHandler }: Props) {
  return (
    <S.saveCommentButtonWrapper margin={margin}>
      <S.saveCommentButton onClick={clickHandler}>
        <I.plus />
        <S.saveCommentButtonText>{buttonText}</S.saveCommentButtonText>
      </S.saveCommentButton>
    </S.saveCommentButtonWrapper>
  );
}
export default SaveButton;
