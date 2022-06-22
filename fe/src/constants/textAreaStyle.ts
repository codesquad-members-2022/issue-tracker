const customStyle = {
  initial: {
    bgColor: "inputBackground",
    lineColor: "line",
  },
  active: {
    bgColor: "offWhite",
    borderColor: "titleActive",
    lineColor: "line",
  },
  typing: {
    bgColor: "offWhite",
    borderColor: "titleActive",
    lineColor: "line",
  },
  typingAfter: {
    bgColor: "offWhite",
    borderColor: "titleActive",
    lineColor: "line",
    countDisplay: "block",
  },
  filled: {
    bgColor: "inputBackground",
    lineColor: "line",
  },
};

const typeStyle = {
  default: {
    inputDisplay: "none",
    placeHolderFont: {
      size: "small",
      color: "placeHolder",
      weight: "regular",
      height: "28px",
    },
  },
  withInput: {
    inputDisplay: "flex",
    placeHolderFont: {
      size: "xSmall",
      color: "label",
      weight: "xRegular",
      height: "20px",
    },
    inputFont: {
      size: "small",
      weight: "regular",
      color: "titleActive",
    },
  },
};

const STYLE: {
  initial: string;
  active: string;
  typing: string;
  typingAfter: string;
  filled: string;
} = {
  initial: "default",
  active: "default",
  typing: "withInput",
  typingAfter: "withInput",
  filled: "withInput",
};

export { STYLE, customStyle, typeStyle };
