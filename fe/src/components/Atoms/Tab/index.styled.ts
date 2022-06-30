import { NavLink } from 'react-router-dom';
import styled, { css } from 'styled-components';

interface tabStyleProps {
  tabStyle: 'STANDARD' | 'FILL';
  border?: 'LEFT' | 'CENTER' | 'RIGHT';
  isActive?: boolean;
}

const StyledNavLink = styled(NavLink).withConfig({
  shouldForwardProp: (prop) => !['tabStyle', 'border'].includes(prop),
})<tabStyleProps>`
  display: flex;
  align-items: center;
  min-width: fit-content;
  ${({ theme }) => theme.fontStyles.linkSmall};
  color: ${({ theme }) => theme.colors.label};

  svg {
    stroke: ${({ theme }) => theme.colors.label};
  }

  &.openCloseFocus {
    color: ${({ theme }) => theme.colors.titleActive};

    svg {
      stroke: ${({ theme }) => theme.colors.titleActive};
    }
  }

  &.active {
    ${({ tabStyle }) => {
      return (
        tabStyle === 'FILL' &&
        css`
          background: ${({ theme }) => theme.colors.line};

          &:hover {
            background: ${({ theme }) => theme.colors.line};
          }
        `
      );
    }}
  }

  ${({ tabStyle }) => {
    return tabStyle === 'FILL'
      ? css`
          padding: 6px 32px;
          background: ${({ theme }) => theme.colors.background};

          svg + span {
            margin: 0 8px;
          }

          &:hover {
            background: ${({ theme }) => theme.colors.inputBackground};
          }
        `
      : css`
          padding: 0px 12px;

          svg + span {
            margin: 0 0 0 4px;
          }
        `;
  }}

  ${({ tabStyle, border }) => {
    if (tabStyle !== 'FILL') return;

    // eslint-disable-next-line default-case
    switch (border) {
      case 'LEFT':
        return css`
          border: 1px solid ${({ theme }) => theme.colors.line};
          border-radius: 11px 0px 0px 11px;
        `;
      case 'CENTER':
        return css`
          border: 1px solid ${({ theme }) => theme.colors.line};
          border-left: none;
        `;
      case 'RIGHT':
        return css`
          border: 1px solid ${({ theme }) => theme.colors.line};
          border-left: none;
          border-radius: 0px 11px 11px 0px;
        `;
    }
  }}
`;

export default StyledNavLink;
