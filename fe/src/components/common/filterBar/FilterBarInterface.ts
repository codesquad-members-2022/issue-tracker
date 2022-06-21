import { MenuList } from "../Panel/PanelInterface";

export interface FilterBarProps {
  menuList: MenuList;
  handlePanelClick?: () => void;
}

export interface StyledFilterBarProps {
  isFocus: boolean;
}
