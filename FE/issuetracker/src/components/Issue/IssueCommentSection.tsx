import * as S from 'components/Issue/styled.issueCommentSection';
import IssueComment from 'components/Issue/IssueComment';
import InputComment from 'components/Issue/InputComment';
import userImageURL from 'assets/images/UserImageLarge.svg';
import userImageURL2 from 'assets/images/UserImageLarge2.svg';

function IssueCommentSection() {
  return (
    <S.commentSection>
      <IssueComment imageURL={userImageURL} />
      <IssueComment imageURL={userImageURL2} />
      <InputComment />
    </S.commentSection>
  );
}
export default IssueCommentSection;
