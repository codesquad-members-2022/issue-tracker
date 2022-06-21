import Search from '@/assets/icons/icon_search.svg';
import Plus from '@/assets/icons/icon_plus.svg';
import Close from '@/assets/icons/icon_close.svg';
import OpenLabel from '@/assets/icons/icon_open_label.svg';
import CloseLabel from '@/assets/icons/icon_close_label.svg';
import Milestone from '@/assets/icons/icon_milestone.svg';
import Label from '@/assets/icons/icon_label.svg';
import ArrowUp from '@/assets/icons/icon_arrow_up.svg';
import ArrowDown from '@/assets/icons/icon_arrow_down.svg';

import styled from 'styled-components';
import { PALETTE } from '@/styles/common';

type IconTypes =
  | 'search'
  | 'plus'
  | 'close'
  | 'openLabel'
  | 'closeLabel'
  | 'milestone'
  | 'label'
  | 'arrowUp'
  | 'arrowDown';

type Icons = Record<IconTypes, any>;

const icons: Icons = {
  search: Search,
  plus: Plus,
  close: Close,
  openLabel: OpenLabel,
  closeLabel: CloseLabel,
  milestone: Milestone,
  label: Label,
  arrowUp: ArrowUp,
  arrowDown: ArrowDown
};

interface IconProps {
  IconType: IconTypes;
  color?: string;
}

function Icon({ IconType, color }: IconProps) {
  const Icon = icons[IconType];
  const $Icon = styled(Icon)`
    path {
      stroke: ${color || PALETTE.WHITE};
    }
  `;
  return <$Icon />;
}

export type { IconTypes };
export { Icon };
