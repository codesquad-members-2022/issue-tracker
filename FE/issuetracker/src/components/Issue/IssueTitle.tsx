import * as S from 'components/Issue/styled.issueTitle';
import * as I from 'design/icons';
import IssueTitleButton from 'components/Issue/IssueTitleButton';

function IssueTitle() {
  return (
    <S.issueTitleWrapper>
      <S.issueTitle>FE 이슈트래커 디자인 시스템 구현</S.issueTitle>
      <S.issueNumber>#2</S.issueNumber>
      <S.buttonWrapper>
        <IssueTitleButton buttonIcon={<I.edit />} buttonText="제목 편집" />
        <IssueTitleButton buttonIcon={<I.archive />} buttonText="이슈 닫기" />
      </S.buttonWrapper>
    </S.issueTitleWrapper>
  );
}
export default IssueTitle;
