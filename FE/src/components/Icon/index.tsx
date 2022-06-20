import { ReactComponent as Search } from '@/assets/icons/icon_search.svg';
import { ReactComponent as Plus } from '@/assets/icons/icon_plus.svg';
import { ReactComponent as Close } from '@/assets/icons/icon_close.svg';
import { ReactComponent as OpenLabel } from '@/assets/icons/icon_open_label.svg';
import { ReactComponent as CloseLabel } from '@/assets/icons/icon_close_label.svg';
import { ReactComponent as Milestone } from '@/assets/icons/icon_milestone.svg';
import { ReactComponent as Label } from '@/assets/icons/icon_label.svg';
import styled from 'styled-components';
import { PALETTE } from '@/styles/common';

type IconTypes = 'search' | 'plus' | 'close' | 'openLabel' | 'closeLabel' | 'milestone' | 'label';

type Icons = Record<IconTypes, any>;

const icons: Icons = {
  search: Search,
  plus: Plus,
  close: Close,
  openLabel: OpenLabel,
  closeLabel: CloseLabel,
  milestone: Milestone,
  label: Label
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
