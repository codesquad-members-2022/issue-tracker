import * as I from 'design/icons';
import * as S from 'components/Issue/styled.inputComment';
import userImageURL from 'assets/images/UserImageLarge.svg';

function InputComment() {
  return (
    <S.inputComment>
      <S.commentWrapper>
        <S.commentUserImage>
          <img src={userImageURL} alt={userImageURL} />
        </S.commentUserImage>
        <S.comment>
          <S.textArea>코멘트를 입력하세요</S.textArea>
          <S.attachFileButtonWrapper>
            <S.attachFileButton>
              <I.paperclip />
              <S.attachFileButtonText>파일 첨부하기</S.attachFileButtonText>
            </S.attachFileButton>
          </S.attachFileButtonWrapper>
        </S.comment>
      </S.commentWrapper>
      <S.saveCommentButtonWrapper>
        <S.saveCommentButton>
          <I.plus />
          <S.saveCommentButtonText>코멘트 작성</S.saveCommentButtonText>
        </S.saveCommentButton>
      </S.saveCommentButtonWrapper>
    </S.inputComment>
  );
}
export default InputComment;
