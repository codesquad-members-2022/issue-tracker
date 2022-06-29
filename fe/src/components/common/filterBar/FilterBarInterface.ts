import { MenuList } from "../panel/PanelInterface";

export interface FilterBarProps {
  menuList: MenuList;
  handlePanelClick?: () => void;
  inputDisplay: string;
  text: string;
}

export interface StyledFilterBarProps {
  isFocus: boolean;
  display?: string;
}
