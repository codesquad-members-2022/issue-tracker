import * as S from 'components/Issue/styled.issueCommentSection';
import IssueComment from 'components/Issue/IssueComment';
<<<<<<< HEAD
import InputComment from 'components/Issue/InputComment';
=======
>>>>>>> origin/11-feat-fe-newissue-레이아웃-구현
import userImageURL from 'assets/images/UserImageLarge.svg';
import userImageURL2 from 'assets/images/UserImageLarge2.svg';

function IssueCommentSection() {
  return (
    <S.commentSection>
      <IssueComment imageURL={userImageURL} />
      <IssueComment imageURL={userImageURL2} />
<<<<<<< HEAD
      <InputComment />
=======
>>>>>>> origin/11-feat-fe-newissue-레이아웃-구현
    </S.commentSection>
  );
}
export default IssueCommentSection;
