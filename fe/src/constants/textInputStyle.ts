interface CommonStyleType {
  textInputDisplay: string;
  titleFont?: {
    size: number;
    weight: number;
    height: number;
    color: string;
  };
  textInputFont?: {
    size: string;
    weight: number;
    height: number;
    color: string;
  };
}

interface SizeType {
  width: number;
  height: number;
}

interface AllSizeType {
  wrapperSize: SizeType;
  titleSize: SizeType;
  textInputSize?: SizeType;
}

interface SizeStyleType {
  basic: {
    large: AllSizeType;
    medium: AllSizeType;
    small: AllSizeType;
  };
  withInput: {
    large: AllSizeType;
    medium: AllSizeType;
    small: AllSizeType;
  };
}

interface StyleType {
  backgroundColor: string;
  border: string;
  opacity?: number;
  titleFont?: {
    size: string;
    weight: number;
    height: number;
    color: string;
  };
}

const specificStyle: {
  initial: StyleType;
  active: StyleType;
  disabled: StyleType;
  typing: StyleType;
  filled: StyleType;
  success: StyleType;
  error: StyleType;
} = {
  initial: {
    backgroundColor: "#EFF0F6",
    border: "",
  },
  active: {
    backgroundColor: "#FEFEFE",
    border: "1px solid #14142b",
  },
  disabled: {
    backgroundColor: "#EFF0F6",
    border: "",
    opacity: 0.5,
  },
  typing: {
    backgroundColor: "#FEFEFE",
    border: "1px solid #14142b",
    titleFont: {
      size: "12px",
      weight: 500,
      height: 20,
      color: "#6E7191",
    },
  },
  filled: {
    backgroundColor: "#EFF0F6",
    border: "",
    titleFont: {
      size: "12px",
      weight: 500,
      height: 20,
      color: "#6E7191",
    },
  },
  success: {
    backgroundColor: "#DDFFE6",
    border: "1px solid #34C759",
    titleFont: {
      size: "12px",
      weight: 500,
      height: 20,
      color: "#00A028",
    },
  },
  error: {
    backgroundColor: "#FFD1CF",
    border: "1px solid #FF3B30",
    titleFont: {
      size: "12px",
      weight: 500,
      height: 20,
      color: "#C60B00",
    },
  },
};

const sizeStyle: SizeStyleType = {
  basic: {
    large: {
      wrapperSize: { width: 340, height: 64 },
      titleSize: { width: 292, height: 28 },
    },
    medium: {
      wrapperSize: { width: 320, height: 56 },
      titleSize: { width: 272, height: 28 },
    },
    small: {
      wrapperSize: { width: 300, height: 40 },
      titleSize: { width: 252, height: 40 },
    },
  },

  withInput: {
    large: {
      wrapperSize: { width: 340, height: 64 },
      titleSize: { width: 292, height: 20 },
      textInputSize: { width: 292, height: 28 },
    },
    medium: {
      wrapperSize: { width: 320, height: 56 },
      titleSize: { width: 272, height: 20 },
      textInputSize: { width: 272, height: 28 },
    },
    small: {
      wrapperSize: { width: 300, height: 40 },
      titleSize: { width: 80, height: 40 },
      textInputSize: { width: 164, height: 40 },
    },
  },
};

const commonStyle: { basic: CommonStyleType; withInput: CommonStyleType } = {
  basic: {
    textInputDisplay: "none",
    titleFont: {
      size: 16,
      weight: 400,
      height: 28,
      color: "#A0A3BD",
    },
  },
  withInput: {
    textInputDisplay: "flex",
    textInputFont: {
      size: "16px",
      weight: 400,
      height: 20,
      color: "#14142B",
    },
  },
};

const STYLE: {
  initial: string;
  active: string;
  disabled: string;
  typing: string;
  filled: string;
  success: string;
  error: string;
} = {
  initial: "basic",
  active: "basic",
  disabled: "basic",
  typing: "withInput",
  filled: "withInput",
  success: "withInput",
  error: "withInput",
};

export { sizeStyle, STYLE, specificStyle, commonStyle };
export type { SizeType };
