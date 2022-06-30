import { Text } from '../common/Text';
import UNDER_ARROW from '@/assets/UnderArrow.svg';
import { ListModal } from '../common/ListModal';
import { LabelFitlerItem } from './LabelFilterItem';
import { SortDetails } from '../common/SortDetails';
import { LabelListItem } from './LabelListItem';

type LabelFilterDetailProps = {
  labelData: {
    name: string;
    color: string;
    description?: string;
    textColor?: string;
  }[];
};

export function LabelFilterDetail({ labelData }: LabelFilterDetailProps) {
  return (
    <SortDetails>
      <summary>
        <Text text="레이블" fontWeight="bold" color="label" />
        <UNDER_ARROW />
      </summary>
      <ListModal listTitle="라벨 필터">
        {labelData.map(({ name, color }) => (
          <LabelListItem
            color={color}
            name={name}
            key={`${name}${Date.now()}`}
          />
        ))}
      </ListModal>
    </SortDetails>
  );
}
