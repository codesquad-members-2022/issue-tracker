import * as I from 'design/icons';
import * as S from 'components/Issue/styled.issueComment';

interface Props {
  imageURL: string;
}

function IssueComment({ imageURL }: Props) {
  return (
    <S.commentArticle>
      <S.commentUserImage>
        <img src={imageURL} alt="user-icon-img" />
      </S.commentUserImage>
      <S.comment>
        <S.commentHeader>
          <S.commentHeaderLeft>
            <S.userName>Oni</S.userName>
            <S.timeStamp>20분 전</S.timeStamp>
          </S.commentHeaderLeft>
          <S.commentHeaderRight>
            <S.writeBadge>작성자</S.writeBadge>
            <S.editButton>
              <I.edit />
              편집
            </S.editButton>
            <S.emoticon>
              <I.smile />
            </S.emoticon>
          </S.commentHeaderRight>
        </S.commentHeader>
        <S.commentContent>
          처음부터 전부 구현하려고 하지 말고 필수적인 기능부터 만든 후, 차근차근 완성도를 높여보세요
        </S.commentContent>
      </S.comment>
    </S.commentArticle>
  );
}

export default IssueComment;
