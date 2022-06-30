import * as S from 'components/IssueList/styled.issues';
import * as I from 'design/icons';
import { useGetData } from 'APIs/Api';
import Issue from 'components/IssueList/Issue';
import { PostIssueType } from 'store/issueList';
import { keyMaker } from 'utils/util';

function Issues() {
  const { data, status } = useGetData(
    'issueList',
    'https://8fe3cd27-6f2c-47dd-8182-62d896d6f37e.mock.pstmn.io/issues?closed=false',
  );
  const issueData: PostIssueType[] = data;
  return (
    <S.IssuesWrap>
      <S.IssuesTab>
        <S.IssuesTabLeft>
          <S.CheckBox type="checkbox" />
          <S.OpenedIssue>
            <I.alertCircle />
            열린 이슈(2)
          </S.OpenedIssue>
          <S.ClosedIssue>
            <I.archive />
            닫힌 이슈(0)
          </S.ClosedIssue>
        </S.IssuesTabLeft>
        <S.IssuesTabRight>
          <S.DropboxContent>
            담당자
            <I.dropdownArrow />
          </S.DropboxContent>
          <S.DropboxContent>
            레이블 <I.dropdownArrow />
          </S.DropboxContent>
          <S.DropboxContent>
            마일스톤 <I.dropdownArrow />
          </S.DropboxContent>
          <S.DropboxContent>
            작성자 <I.dropdownArrow />
          </S.DropboxContent>
        </S.IssuesTabRight>
      </S.IssuesTab>
      {status === 'success' &&
        issueData.map((issue) => {
          const key = keyMaker();
          return <Issue key={key} data={issue} />;
        })}
    </S.IssuesWrap>
  );
}

export default Issues;
