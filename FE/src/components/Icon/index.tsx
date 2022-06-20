import { ReactComponent as Search } from '@/assets/icons/icon_search.svg';
import { ReactComponent as Plus } from '@/assets/icons/icon_plus.svg';
import { ReactComponent as Close } from '@/assets/icons/icon_close.svg';
import styled from 'styled-components';
import { PALETTE } from '@/styles/common';

type IconTypes = 'search' | 'plus' | 'close';

type Icons = Record<IconTypes, any>;

const icons: Icons = {
  search: Search,
  plus: Plus,
  close: Close
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
