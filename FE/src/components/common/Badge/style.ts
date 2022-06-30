import styled, { css } from 'styled-components';
import { theme } from '@/styles/theme';

export type BadgeStyledProps = {
  badgeSize?: keyof typeof theme.badgeSize;
  styles?: string;
};

export const Badge = styled.figure<BadgeStyledProps>`
  cursor: pointer;
  overflow: hidden;
  box-shadow: 0px 0px 0px 10px ${theme.color.line} inset;
  border-radius: 50%;

  width: ${({ badgeSize = 'base' }) => theme.badgeSize[badgeSize]};
  height: ${({ badgeSize = 'base' }) => theme.badgeSize[badgeSize]};
`;
