import Button from '@/components/common/Button';
import { Icon } from '@/components/common/Icon';
import { $IssueListTabs } from '@/components/IssueList/IssueListTabs/style';

export default function IssueListTabs() {
  return (
    <$IssueListTabs>
      <Button styleType="mediumText">
        <Icon iconType="openLabel" />
        {`열린 이슈(2)`}
      </Button>
      <Button styleType="mediumText">
        <Icon iconType="closeLabel" />
        {`닫힌 이슈(0)`}
      </Button>
    </$IssueListTabs>
  );
}
