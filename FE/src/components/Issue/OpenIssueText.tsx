import { Link } from 'react-router-dom';
import { useRecoilState } from 'recoil';
import { Text } from '@/components/common/Text';
import { getIssueOpen } from '@/recoil/selectors/getIssueOpen';

type OpenIssueTextProps = {
  openIssueCount: number;
};

export function OpenIssueText({ openIssueCount }: OpenIssueTextProps) {
  const [open, setOpen] = useRecoilState(getIssueOpen);

  return (
    <Link to="/issueList/open" onClick={() => setOpen(true)}>
      <Text
        text={`열린 이슈(${openIssueCount})`}
        color="titleArchieve"
        fontWeight={open ? 'bold' : 'base'}
      />
    </Link>
  );
}
