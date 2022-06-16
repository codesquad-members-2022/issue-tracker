import styled, { css } from 'styled-components';
import { Link } from 'react-router-dom';
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

const handleBorderRadiusType = css<{ size: string }>`
  ${({ size }) =>
    size === 'sm' &&
    css`
      width: 340px;
      height: 40px;
    `}
  ${({ size }) =>
    size === 'md' &&
    css`
      width: 600px;
      height: 40px;
      border-radius: 24px;
    `}
`;

/* const handleBorderRadiusTypes = (size: string): string  => {
  switch (size) {
    case 'sm':
      return '11px';
    case 'md':
      return '20px';
    case 'lg':
      return '24px';
    default:
      return ;
  }
}; */

export const CustomBtn = styled(Link)<{ size: string }>`
  ${BtnBase};
  width: ${({ size }) => handleWidthType(size)};
  height: ${({ size }) => handleHeightType(size)};
  color: ${({ theme: { colors } }) => colors.offWhite};
  background-color: ${({ theme: { colors } }) => colors.primary};
  ${handleBorderRadiusType}
`;
