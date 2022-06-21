import { MenuList } from "../panel/PanelInterface";

export interface FilterBarProps {
  menuList: MenuList;
  handlePanelClick?: () => void;
}

export interface StyledFilterBarProps {
  isFocus: boolean;
}
