export const buttonStyle = {
  size: {
    large: {
      width: 340,
      height: 64,
    },
    medium: {
      width: 240,
      height: 56,
    },
    small: {
      width: 120,
      height: 40,
    },
  },
  standard: {
    initial: {
      bgColor: "primary",
      fontColor: "offWhite",
      borderColor: "none",
    },
    hover: {
      bgColor: "primaryDark",
      fontColor: "offWhite",
      borderColor: "none",
    },
  },
  secondary: {
    initial: {
      bgColor: "offWhite",
      fontColor: "primary",
      borderColor: "primary",
    },
    hover: {
      bgColor: "offWhite",
      fontColor: "primaryDark",
      borderColor: "primaryDark",
    },
  },
  focus: {
    borderColor: "primaryLight",
  },
  fontSize: {
    large: "small",
    medium: "small",
    small: "xSmall",
  },
  borderRadius: {
    large: 20,
    medium: 20,
    small: 11,
  },
  disabled: {
    opacity: 0.5,
  },
};

export const textButtonStyle = {
  medium: {
    width: 87,
    height: 32,
    fontSize: "small",
  },
  small: {
    width: 70,
    height: 32,
    fontSize: "xSmall",
  },
  fontColor: {
    initial: "label",
    active: "titleActive",
    hover: "body",
  },
};
