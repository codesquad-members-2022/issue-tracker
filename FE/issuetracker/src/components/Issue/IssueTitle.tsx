import * as S from 'components/Issue/styled.issueTitle';
import TitleButton from 'components/Issue/TitleButton';

function IssueTitle() {
  return (
    <S.issueTitleWrapper>
      <S.issueTitle>FE 이슈트래커 디자인 시스템 구현</S.issueTitle>
      <S.issueNumber>#2</S.issueNumber>
      <S.buttonWrapper>
        <TitleButton buttonType="editButton" buttonText="제목 편집" />
        <TitleButton buttonType="archiveButton" buttonText="이슈 닫기" />
      </S.buttonWrapper>
    </S.issueTitleWrapper>
  );
}
export default IssueTitle;
