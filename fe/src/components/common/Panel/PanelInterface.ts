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
  width: number;
  position: "left" | "right";
  mg?: string;
  menuList: MenuList;
  type?: "checkbox" | "list";
  isOpen?: boolean;
  onClick?: () => void;
}

export interface StyledPanelProps {
  width: number;
  position: "left" | "right";
  isOpen: boolean;
  mg: string;
}
