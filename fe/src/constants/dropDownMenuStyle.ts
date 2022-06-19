export const indicatorStyle = {
  color: {
    initial: "label",
    hover: "body",
  },
  fontSize: "small",
  fontWeight: "bold",
};

export const panelStyle = {
  default: {
    bgColor: "offWhite",
    fontSize: "small",
    fontWeight: "regular",
    fontColor: "body",
    lineHeight: 28,
  },
  title: {
    bgColor: "background",
    fontSize: "medium",
    fontColor: "titleActive",
    lineHeight: 32,
  },
  borderColor: "line",
};

export interface DropDownStyle {
  indicator: { width: number; height: number; gap: number };
  panel: { width: number; position: "left" | "right"; mg?: string };
}

export const DropDownDefaultStyle: DropDownStyle = {
  indicator: {
    width: 62,
    height: 32,
    gap: 8,
  },
  panel: {
    width: 240,
    position: "left",
    mg: "10px 0 0 0",
  },
};
