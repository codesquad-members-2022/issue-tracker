import { useRecoilValue } from 'recoil';
import { Link } from 'react-router-dom';
import * as S from './style';
import { LabelNavItem } from '@/components/Label/LabelNavItem';
import { MilestonNavItem } from '@/components/Milestone/MilestoneNavItem';
import { Button } from '@/components/common/Button';
import { getLabels } from '@/recoil/selectors/getLabels';
import { getMilestones } from '@/recoil/selectors/getMilestones';

export function NavigationTab() {
  const { labels } = useRecoilValue(getLabels);
  const { milestones } = useRecoilValue(getMilestones);

  return (
    <S.Layout>
      <S.NavigationTab>
        <LabelNavItem labelLength={labels.length} />
        <MilestonNavItem milestoneLength={milestones.length} />
      </S.NavigationTab>
      <Link to="/issueAdd">
        <Button size="small" color="primary" fontSize="base">
          이슈 작성
        </Button>
      </Link>
    </S.Layout>
  );
}
