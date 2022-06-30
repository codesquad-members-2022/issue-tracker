import { useRecoilValue } from 'recoil';
import * as S from './style';

import { LabelFilterDetail } from '@/components/Label/LabelFilterDetail';
import { MilestoneFilterDetail } from '@/components/Milestone/MilestoneFilterDetail';
import { AssigneeFilterDetail } from '@/components/User/AssigneeFilterDetail';
import { AuthorFilterDetail } from '@/components/User/AuthorFilterDetail';
import { getAssginees } from '@/recoil/selectors/getAssignees';
import { getLabels } from '@/recoil/selectors/getLabels';
import { getMilestones } from '@/recoil/selectors/getMilestones';

export function IssueListFilters() {
  const { labels } = useRecoilValue(getLabels);
  const { milestones } = useRecoilValue(getMilestones);
  const { assignees } = useRecoilValue(getAssginees);
  return (
    <S.IssueListFilters>
      <AuthorFilterDetail userData={assignees} />
      <LabelFilterDetail labelData={labels} />
      <MilestoneFilterDetail milestoneList={milestones} />
      <AssigneeFilterDetail userData={assignees} />
    </S.IssueListFilters>
  );
}
