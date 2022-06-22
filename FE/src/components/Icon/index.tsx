import Search from '@/assets/icons/icon_search.svg';
import Plus from '@/assets/icons/icon_plus.svg';
import Close from '@/assets/icons/icon_close.svg';
import OpenLabel from '@/assets/icons/icon_open_label.svg';
import CloseLabel from '@/assets/icons/icon_close_label.svg';
import Milestone from '@/assets/icons/icon_milestone.svg';
import Label from '@/assets/icons/icon_label.svg';
import ArrowUp from '@/assets/icons/icon_arrow_up.svg';
import ArrowDown from '@/assets/icons/icon_arrow_down.svg';
import RadioOff from '@/assets/icons/icon_radio_off.svg';
import RadioOn from '@/assets/icons/icon_radio_on.svg';

import styled from 'styled-components';

type IconTypes =
  | 'search'
  | 'plus'
  | 'close'
  | 'openLabel'
  | 'closeLabel'
  | 'milestone'
  | 'label'
  | 'arrowUp'
  | 'arrowDown'
  | 'radioOff'
  | 'radioOn';

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
  arrowDown: ArrowDown,
  radioOff: RadioOff,
  radioOn: RadioOn
};

interface IconProps {
  iconType: IconTypes;
  color?: string;
}

function Icon({ iconType, color }: IconProps) {
  const Icon = icons[iconType];
  const $Icon = styled(Icon)`
    path {
      stroke: ${color};
    }
  `;
  return color ? <$Icon /> : <Icon />;
}

export type { IconTypes };
export { Icon };
