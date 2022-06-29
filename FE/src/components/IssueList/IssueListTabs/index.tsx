import Button from '@/components/common/Button';
import { Icon } from '@/components/common/Icon';
import { $IssueListTabs } from '@/components/IssueList/IssueListTabs/style';
import { useFilterCondition, useFilterConditionDispatch } from '@/contexts/FilterCondition';
import { setCondition } from '@/contexts/FilterCondition/action';
import { useIssueCountData } from '@/hooks/useIssueListData';
import { COLOR } from '@/styles/common';
import { IssueStatusType } from '@/types/common';

export default function IssueListTabs() {
  const { status: currentStatus } = useFilterCondition();
  const dispatch = useFilterConditionDispatch();
  const { isSuccess: isSuccessOfOpen, data: issueOpenCounts } = useIssueCountData('open');
  const { isSuccess: isSuccessOfClose, data: issueCloseCounts } = useIssueCountData('close');
  return (
    <$IssueListTabs>
      <Button
        styleType="mediumText"
        color={currentStatus === 'OPEN' ? COLOR.title : ''}
      >
        <Icon iconType="openLabel" />
        {`열린 이슈 ${isSuccessOfOpen ? `(${issueOpenCounts?.data.total_count})` : ''}`}
      </Button>
      <Button
        styleType="mediumText"
        color={currentStatus === 'CLOSE' ? COLOR.title : ''}
      >
        <Icon iconType="closeLabel" />
        {`닫힌 이슈 ${isSuccessOfClose ? `(${issueCloseCounts?.data.total_count})` : ''}`}
      </Button>
    </$IssueListTabs>
  );
}
