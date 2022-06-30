import { useRecoilState } from 'recoil';
import { Link } from 'react-router-dom';
import { Text } from '@/components/common/Text';
import { getIssueOpen } from '@/recoil/selectors/getIssueOpen';

type CloseIssueTextProps = {
  closeIssueCount: number;
};

export function CloseIssueText({ closeIssueCount }: CloseIssueTextProps) {
  const [open, setOpen] = useRecoilState(getIssueOpen);

  return (
    <Link to="/issueList/close" onClick={() => setOpen(false)}>
      <Text
        text={`닫힌 이슈(${closeIssueCount})`}
        color="titleArchieve"
        fontWeight={!open ? 'bold' : 'base'}
      />
    </Link>
  );
}
