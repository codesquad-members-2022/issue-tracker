import { useRecoilValue } from 'recoil';
import { getIssue } from '@/recoil/selectors/getIssue';
import * as S from './style';
import { LabelFilterDetail } from '@/components/Label/LabelFilterDetail';
import { MilestoneFilterDetail } from '@/components/Milestone/MilestoneFilterDetail';
import { AssigneeFilterDetail } from '@/components/User/AssigneeFilterDetail';
import { AuthorFilterDetail } from '@/components/User/AuthorFilterDetail';
import {
  ListTable,
  ListTableHeader,
  ListTableItems,
} from '@/components/common/ListTable';
import { IssueItem, IssueItemProps } from '@/components/Issue/IssueItem';
import { CheckWhetherOpen } from '@/components/Issue/CheckWhetherOpen';
import { getLabels } from '@/recoil/selectors/getLabels';
import { getMilestones } from '@/recoil/selectors/getMilestones';
import { getAssginees } from '@/recoil/selectors/getAssignees';
import { getIssueOpen } from '@/recoil/selectors/getIssueOpen';

// TODO: service 디렉토리로 분리
type filterBooleanCountProps = {
  (array: [], target: string, boolean: boolean): number;
};

const filterBooleanCount: filterBooleanCountProps = (
  array,
  target,
  boolean,
) => {
  const count = array.filter(value => value[target] === boolean);

  return count.length;
};

type Override<T1, T2> = Omit<T1, keyof T2> & T2;

type UserIssueProps = Override<IssueItemProps, { timestamp: Date }>;

const getDiffrentMinutes = (timestamp: string): number => {
  const now = new Date();
  const copiedTimestamp = new Date(timestamp);

  const differentMinutes =
    (now.getTime() - copiedTimestamp.getTime()) / (1000 * 60);

  return Math.floor(differentMinutes);
};

const getTrimmedMessage = (minutes: number): string => {
  if (minutes < 1) {
    return '1분 전';
  }
  if (minutes < 60) {
    return `${minutes}분`;
  }
  if (minutes < 1440) {
    return `${Math.floor(minutes / 60)}시간`;
  }
  return `${Math.floor(minutes / 1440)}일`;
};

export function IssueList() {
  const { issues } = useRecoilValue(getIssue);
  const { labels } = useRecoilValue(getLabels);
  const { milestones } = useRecoilValue(getMilestones);
  const { assignees } = useRecoilValue(getAssginees);
  const openState = useRecoilValue(getIssueOpen);

  const filteredIssueItems = issues.map(
    (issue: IssueItemProps) =>
      issue.open === openState && (
        <IssueItem
          issueTitle={issue.issueTitle}
          issueNumber={issue.issueNumber}
          issueWriter={issue.issueWriter}
          timestamp={getTrimmedMessage(getDiffrentMinutes(issue.timestamp))}
          milestone={issue.milestone}
          label={issue.label}
          assignee={issue.assignee}
          key={`${issue.issueTitle}${Date.now()}`}
        />
      ),
  );

  return (
    <ListTable>
      <ListTableHeader>
        <S.IssueHeaderCheckOpen>
          <input type="checkbox" />
          <CheckWhetherOpen
            openIssueCount={filterBooleanCount(issues, 'open', true)}
            closeIssueCount={filterBooleanCount(issues, 'open', false)}
          />
        </S.IssueHeaderCheckOpen>

        {/* TODO: 템플릿으로 분리 */}
        <S.IssueHeaderFilters>
          <AuthorFilterDetail userData={assignees} />
          <LabelFilterDetail labelData={labels} />
          <MilestoneFilterDetail milestoneList={milestones} />
          <AssigneeFilterDetail userData={assignees} />
        </S.IssueHeaderFilters>
      </ListTableHeader>

      <ListTableItems>{filteredIssueItems}</ListTableItems>
    </ListTable>
  );
}
