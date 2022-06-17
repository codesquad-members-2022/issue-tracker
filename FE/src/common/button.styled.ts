import styled, { css } from 'styled-components';
import { Link } from 'react-router-dom';
import { BtnBase } from './util.styled';

export const BtnStyles = css<{ size: string }>`
  ${({ size }) =>
    size === 'sm' &&
    css`
      width: 120px;
      height: 40px;
      border-radius: 11px;
    `}
  ${({ size }) =>
    size === 'md' &&
    css`
      width: 240px;
      height: 56px;
      border-radius: 20px;
    `}
    ${({ size, theme: { fontWeights, fontSizes, lineHeights, colors } }) =>
    size === 'lg' &&
    css`
      width: 360px;
      height: 64px;
      border-radius: 24px;
      font-weight: ${fontWeights.bold};
      font-size: ${fontSizes.md};
      line-height: ${lineHeights.base};
      color: ${colors.offWhite};
    `}
`;

export const CustomBtn = styled(Link)<{ size: string }>`
  ${BtnBase};
  ${BtnStyles}
  ${({ theme: { colors } }) => css`
    color: ${colors.offWhite};
    background-color: ${colors.primary};
  `}
`;
