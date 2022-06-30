import { CloseIssueText } from './CloseIssueText';
import { OpenIssueText } from './OpenIssueText';

type CountType = {
  openIssueCount: number;
  closeIssueCount: number;
};

export function CheckWhetherOpen({
  openIssueCount,
  closeIssueCount,
}: CountType) {
  return (
    <>
      <OpenIssueText openIssueCount={openIssueCount} />
      <CloseIssueText closeIssueCount={closeIssueCount} />
    </>
  );
}
