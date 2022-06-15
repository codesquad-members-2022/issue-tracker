import styled from 'styled-components';
import { NavLink } from 'react-router-dom';
import { BtnBase } from './util.styled';

const handleWidthType = (size: string): string => {
  switch (size) {
    case 'sm':
      return '120px';
    case 'md':
      return '240px';
    case 'lg':
      return '360px';
    default:
      return '';
  }
};

const handleHeightType = (size: string): string => {
  switch (size) {
    case 'sm':
      return '40px';
    case 'md':
      return '56px';
    case 'lg':
      return '64px';
    default:
      return '';
  }
};

const handleBorderRadiusType = (size: string): string => {
  switch (size) {
    case 'sm':
      return '11px';
    case 'md':
      return '20px';
    case 'lg':
      return '20px';
    default:
      return '';
  }
};

export const CustomBtn = styled(NavLink)<{ size: string }>`
  ${BtnBase};
  width: ${({ size }) => handleWidthType(size)};
  height: ${({ size }) => handleHeightType(size)};
  border-radius: ${({ size }) => handleBorderRadiusType(size)};
  color: ${({ theme: { colors } }) => colors.offWhite};
  background-color: ${({ theme: { colors } }) => colors.primary};
`;
