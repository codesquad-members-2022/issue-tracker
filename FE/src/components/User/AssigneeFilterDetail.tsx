import { Text } from '../common/Text';
import UNDER_ARROW from '@/assets/UnderArrow.svg';
import { ListModal } from '../common/ListModal';
import { SortDetails } from '../common/SortDetails';
import { AssigneeListItem } from './AssigneeListItem';

type AssigneeFilterDetailProps = {
  userData: { name: string; profileImage: string }[];
};

export function AssigneeFilterDetail({ userData }: AssigneeFilterDetailProps) {
  return (
    <SortDetails>
      <summary>
        <Text text="담당자" fontWeight="bold" color="label" />
        <UNDER_ARROW />
      </summary>
      <ListModal listTitle="담당자 필터">
        {userData.map(({ name, profileImage }) => (
          <AssigneeListItem
            assignee={name}
            assigneeProfileImg={profileImage}
            key={`${name}${Date.now()}`}
          />
        ))}
      </ListModal>
    </SortDetails>
  );
}
