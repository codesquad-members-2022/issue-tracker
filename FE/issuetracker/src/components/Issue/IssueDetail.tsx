import { useRecoilValue } from 'recoil';
import * as S from 'components/Issue/styled/issueDetail';
import IssueLabel from 'components/Issue/IssueLabel';
import { issueState } from 'context/issue';

function IssueDetail() {
  const issueData = useRecoilValue(issueState);
  return (
    <S.issueDetailWrapper>
      <IssueLabel isIssueClosed={issueData.closed} />
      <S.issueDetail>이 이슈가 20분 전에 Oni님에 의해 열렸습니다 - 코멘트 1개</S.issueDetail>
    </S.issueDetailWrapper>
  );
}
export default IssueDetail;
