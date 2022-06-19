export interface ThumbnailData {
  url?: string;
  hex?: string;
}

export interface MenuList {
  title: string;
  items: PanelMenuItemProps[];
}

export interface PanelMenuItemProps {
  type?: string;
  text: string;
  thumbnail?: ThumbnailData;
  onClick?: () => void;
}

export interface PanelProps {
  width?: number;
  menuList?: MenuList;
  type?: "checkbox" | "list";
  onClick?: () => void;
}

export interface StyledPanelProps {
  width: number;
}
