const PALETTE = {
  BLACK: '#14142B',
  GREY: {
    100: '#4E4B66',
    200: '#6E7191',
    300: '#A0A3BD',
    400: '#D9DBE9',
    500: '#EFF0F6',
    600: '#F7F7FC'
  },
  RED: {
    100: '#FFD1CF',
    200: '#FF3B30',
    300: '#C60B00'
  },
  GREEN: {
    100: '#DDFFE6',
    200: '#34C759',
    300: '#00A028'
  },
  BLUE: {
    100: '#C7EBFF',
    200: '#007AFF',
    300: '#004DE3',
    DARK: {
      100: '#CCD4FF',
      200: '#0025E7',
      300: '#020070'
    }
  },
  WHITE: '#FEFEFE'
};

const COLOR = {
  title: PALETTE.BLACK,
  body: PALETTE.GREY[100],
  label: PALETTE.GREY[200],
  placeholder: PALETTE.GREY[300],
  line: PALETTE.GREY[400],
  inputBackgound: PALETTE.GREY[500],
  background: PALETTE.GREY[600],
  primary: {
    initail: PALETTE.BLUE[200],
    focus: PALETTE.BLUE[100],
    hover: PALETTE.BLUE[300]
  },
  secondary: {
    initail: PALETTE.BLUE.DARK[200],
    focus: PALETTE.BLUE.DARK[100],
    hover: PALETTE.BLUE.DARK[300]
  },
  error: {
    initail: PALETTE.RED[200],
    focus: PALETTE.RED[100],
    hover: PALETTE.RED[300]
  },
  success: {
    initail: PALETTE.GREEN[200],
    focus: PALETTE.GREEN[100],
    hover: PALETTE.GREEN[300]
  }
};

export { PALETTE, COLOR };
