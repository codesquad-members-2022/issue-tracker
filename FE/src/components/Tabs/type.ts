import { IconTypes } from '@/components/Icon';

type listItem = {
  name: string;
  iconType?: IconTypes;
  count?: number;
};

interface ITabs {
  list: listItem[];
}

export type { listItem, ITabs };
