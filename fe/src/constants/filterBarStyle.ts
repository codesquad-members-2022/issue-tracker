export const filterBarStyle = {
  overall: {
    size: {
      width: 600,
      height: 40,
    },
    initial: {
      borderColor: "line",
    },
    active: {
      borderColor: "titleAction",
    },
  },
  filter: {
    width: 128,
    initial: {
      bgColor: "background",
      borderColor: "line",
    },
    hover: {
      bgColor: "line",
    },
    active: {
      bgColor: "offWhite",
    },
  },
  input: {
    initial: {
      bgColor: "inputBackground",
      fontColor: "placeHolder",
    },
    active: {
      bgColor: "offWhite",
      fontColor: "titleActive",
    },
  },
};

export const filterStyle = {
  indicator: { width: 128, height: 40, gap: 35 },
  panel: {
    width: 240,
    position: "left",
    top: 50,
  },
};
