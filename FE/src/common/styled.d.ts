import 'styled-components';

declare module 'styled-components' {
  export interface DefaultTheme {
    fontSizes: {
      xs: '0.75rem';
      sm: '1rem';
      md: '1.125rem';
      lg: '1.5rem';
      xl: '2rem';
    };
    fontWeights: {
      normal: '400';
      bold: '700';
    };
    lineHeights: {
      shorter: '1.25rem';
      short: '1.75rem';
      base: '2rem';
      tall: '2.5rem';
      taller: '3rem';
    };
    colors: {
      titleActive: '#14142B';
      body: '#4E4B66';
      label: '#6E7191';
      placeholder: '#A0A3BD';
      line: '#D9DBE9';
      inputBackground: '#EFF0F6';
      background: '#F7F7FC';
      offWhite: '#FEFEFE';
      primary: '#007AFF';
      primaryLight: '#C7EBFF';
      primaryDark: '#004DE3';
      secondary: '#0025E7';
      secondaryLight: '#CCD4FF';
      secondaryDark: '#020070';
      error: '#FF3B30';
      errorLight: '#FFD1CF';
      errorDark: '#C60B00';
      success: '#34C759';
      successLight: '#DDFFE6';
      successDark: '#00A028';
      grey1: '#D9D9D9';
    };
  }
}

export default theme;
