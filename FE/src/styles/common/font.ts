const FONT = {
  SIZE: {
    X_SMALL: '1.2rem',
    SMALL: '1.4rem',
    BASE: '1.6rem',
    MEDIUM: '1.8rem',
    LARGE: '2.4rem'
  },
  WEIGHT: {
    REGULAR: 400,
    MEDIUM: 500,
    BOLD: 700
  },
  FAMILY: {
    BASE: "'Noto Sans KR', sans-serif",
    LOGO: "'Montserrat', sans-serif"
  }
};

const TYPOGRAPHY = {
  logo: {
    large: `${FONT.WEIGHT.REGULAR} 5.6rem/1.5 ${FONT.FAMILY.LOGO}`,
    medium: `${FONT.WEIGHT.MEDIUM} 3.2rem/1.5 ${FONT.FAMILY.LOGO}`
  },
  display: {
    regular: `${FONT.WEIGHT.REGULAR} 3.2rem/1.5 ${FONT.FAMILY.BASE}`,
    bold: `${FONT.WEIGHT.BOLD} 3.2rem/1.5 ${FONT.FAMILY.BASE}`
  }
};

export { FONT, TYPOGRAPHY };
