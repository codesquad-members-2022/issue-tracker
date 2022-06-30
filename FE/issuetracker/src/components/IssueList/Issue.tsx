import Label, { AccountImg } from 'components/common/Common';

import * as S from 'components/IssueList/styled.issue';
import * as I from 'design/icons';

import AccountSrc from 'assets/images/UserImageLarge.svg';
import { CheckBox } from 'components/IssueList/styled.issues';
import { PostIssueType } from 'store/issueList';
import { keyMaker } from 'utils/util';

function Issue({ data }: { data: PostIssueType }) {
  const title = 'FE';
  const color = 'blue';
  const labels = data.labels.map((label) => {
    const key = keyMaker();
    return <Label key={key} title={label.title} color={label.color} />;
  });
  return (
    <S.IssueWrap>
      <S.IssueLeft>
        <S.IssueTop>
          <CheckBox type="checkbox" />
          <S.AlertCircleFigure>
            <I.alertCircle />
          </S.AlertCircleFigure>
          <S.Title>{data.title}</S.Title>
          {labels}
        </S.IssueTop>
        <S.IssueBottom>
          <S.IssueBottomContent>{data.id}</S.IssueBottomContent>
          <S.IssueBottomContent>{data.writer.name}</S.IssueBottomContent>
          <S.IssueBottomContent>
            <I.milestone />
            {data.milestone.title}
          </S.IssueBottomContent>
        </S.IssueBottom>
      </S.IssueLeft>
      <AccountImg src={data.writer.profileImage} />
    </S.IssueWrap>
  );
}

export default Issue;
