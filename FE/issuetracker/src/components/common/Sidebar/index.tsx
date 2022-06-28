import * as S from 'components/common/Sidebar/styled';
import { newIssueState } from 'context/newIssue';
import { useRecoilValue } from 'recoil';
import { keyMaker } from 'utils/util';
import Label, { ProgressBar } from '../Common';
import DropDown from './Dropdown';

function SideBar() {
  const { assignees, mileStone, labels } = useRecoilValue(newIssueState);
  const labelContents =
    labels[0].title !== ''
      ? labels.map((label) => {
          const key = keyMaker();
          return <Label key={key} color={label.color} title={label.title} />;
        })
      : '';
  const assigneesCotents =
    assignees[0].name !== ''
      ? assignees.map((assignee) => {
          const key = keyMaker();
          return (
            <S.DropDownContent key={key}>
              <S.SmallAcountImg src={assignee.profileImage} />
              {assignee.name}
            </S.DropDownContent>
          );
        })
      : '';
  const mileStoneContent =
    mileStone.title !== '' ? (
      <>
        <ProgressBar
          percent={(mileStone.closedIssue / (mileStone.openedIssue + mileStone.closedIssue)) * 100}
        />
        {mileStone.title}
      </>
    ) : (
      ''
    );

  const first = true;
  const subjects: Array<string> = ['담당자', '레이블', '마일스톤'];
  return (
    <S.AdditionalContents>
      <S.AdditionalContent first={first}>
        <S.ContentTitleAndButton>
          담당자
          <DropDown subject={subjects[0]} />
        </S.ContentTitleAndButton>
        <S.Status>{assigneesCotents}</S.Status>
      </S.AdditionalContent>
      <S.AdditionalContent first={!first}>
        <S.ContentTitleAndButton>
          레이블
          <DropDown subject={subjects[1]} />
        </S.ContentTitleAndButton>
        <S.Status>{labelContents}</S.Status>
      </S.AdditionalContent>
      <S.AdditionalContent first={!first}>
        <S.ContentTitleAndButton>
          마일스톤
          <DropDown subject={subjects[2]} />
        </S.ContentTitleAndButton>
        <S.Status>{mileStoneContent}</S.Status>
      </S.AdditionalContent>
    </S.AdditionalContents>
  );
}
export default SideBar;
