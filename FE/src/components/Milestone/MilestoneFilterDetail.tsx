import { Text } from '../common/Text';
import UNDER_ARROW from '@/assets/UnderArrow.svg';
import { ListModal } from '../common/ListModal';
import { SortDetails } from '../common/SortDetails';
import { MilestoneSideBarDetailListItem } from './MilestoneSideBarDetailListItem';

type MilestoneFilterDetailFilterDetailProps = {
  milestoneList: {
    title: string;
    description: string;
    dueDate: string;
  }[];
};

export function MilestoneFilterDetail({
  milestoneList,
}: MilestoneFilterDetailFilterDetailProps) {
  return (
    <SortDetails>
      <summary>
        <Text text="마일스톤" fontWeight="bold" color="label" />
        <UNDER_ARROW />
      </summary>
      <ListModal listTitle="마일스톤 필터">
        {milestoneList.map(({ title }) => (
          <MilestoneSideBarDetailListItem
            title={title}
            key={`${title}${Date.now()}`}
          />
        ))}
      </ListModal>
    </SortDetails>
  );
}
