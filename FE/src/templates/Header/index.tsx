import { Link } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import * as S from './style';
import LOGO_IMG from '@/assets/LogoIMG.svg';
import { Badge } from '@/components/common/Badge';
import { getIssueOpen } from '@/recoil/selectors/getIssueOpen';

export function Header() {
  const setOpen = useSetRecoilState(getIssueOpen);

  return (
    <S.Header>
      <Link to="/issueList/open" onClick={() => setOpen(true)}>
        <LOGO_IMG />
      </Link>
      <Badge
        imgSRC="https://ca.slack-edge.com/T74H5245A-U02RA7K501L-3c55992367ca-512"
        alt="profile image"
      />
    </S.Header>
  );
}
