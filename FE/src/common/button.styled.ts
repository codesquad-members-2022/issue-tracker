import styled from 'styled-components';
import { NavLink } from 'react-router-dom';
import theme from './theme';

const handleBackgroundColorType = (color?: string): string => {
  switch (color) {
    case 'primary':
      return '340px';
    case 'primaryLight':
      return theme.colors.primaryLight;
    case 'primaryDark':
      return theme.colors.primaryDark;
    default:
      return '#000';
  }
};

export const BaseBtn = styled(NavLink)<{ color?: string }>`
  display: block;
  width: 340px;
  height: 64px;
  border-radius: 20px;
  color: ${({ theme: { colors } }) => colors.offWhite};
  background-color: ${({ color }) => handleBackgroundColorType(color)};
  text-align: center;
  line-height: 64px;
`;
