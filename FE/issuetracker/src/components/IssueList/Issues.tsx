import * as S from 'components/IssueList/styled.issues';

function Issues() {
  return (
    <S.IssuesWrap>
      <S.IssuesTab>
        <S.IssuesTabLeft>
          <S.CheckBox type="checkbox" />
          <S.OpenedIssue>열린 이슈(2)</S.OpenedIssue>
          <S.ClosedIssue>닫힌 이슈(0)</S.ClosedIssue>
        </S.IssuesTabLeft>
        <S.IssuesTabRight>
          <S.DropboxContent>담당자</S.DropboxContent>
          <S.DropboxContent>레이블</S.DropboxContent>
          <S.DropboxContent>마일스톤</S.DropboxContent>
          <S.DropboxContent>작성자</S.DropboxContent>
        </S.IssuesTabRight>
      </S.IssuesTab>
    </S.IssuesWrap>
  );
}

export default Issues;
