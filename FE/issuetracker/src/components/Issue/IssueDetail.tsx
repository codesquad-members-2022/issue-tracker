import * as I from 'design/icons';
import * as S from 'components/Issue/styled.issueDetail';

function IssueDetail() {
  return (
    <S.issueDetailWrapper>
      <S.issueLabel>
        <I.alertCircle />
        <S.labelText>열린 이슈</S.labelText>
      </S.issueLabel>
      <S.issueDetail>이 이슈가 20분 전에 Oni님에 의해 열렸습니다 - 코멘트 1개</S.issueDetail>
    </S.issueDetailWrapper>
  );
}
export default IssueDetail;
