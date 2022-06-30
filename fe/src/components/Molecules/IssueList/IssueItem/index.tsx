import CheckBox from 'components/Atoms/CheckBox';
import UserImage, { UserImageTypes } from 'components/Atoms/UserImage';
import { LabelTypes } from 'components/Atoms/Label';

import Issue from 'components/Molecules/IssueList/Issue';
import { CheckIssue, AssigneeDiv } from 'components/Molecules/IssueList/IssueItem/index.styles';
import CommonItem from 'components/Molecules/CommonList/CommonItem';

export interface IssueItemTypes {
  id: number;
  status: string;
  title: string;
  labels: LabelTypes[];
  createdAt: string;
  milestone: string;
  assignees: UserImageTypes[];
  writer: UserImageTypes;
  checkedItemHandler: (id: string, isChecked: boolean) => void;
  checkedIssue: string[];
}

const IssueItem = (props: IssueItemTypes) => {
  const { id, assignees, checkedItemHandler, checkedIssue } = props;

  const assigneeList = assignees.map((user) => {
    return <UserImage {...user} key={`assignees-${user.id}`} />;
  });

  return (
    <CommonItem>
      <CheckIssue>
        <CheckBox id={id} checkedItemHandler={checkedItemHandler} checkedIssue={checkedIssue} />
        <Issue {...props} />
      </CheckIssue>
      <AssigneeDiv>{assigneeList}</AssigneeDiv>
    </CommonItem>
  );
};

export default IssueItem;
