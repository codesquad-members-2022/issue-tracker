import styled, { css } from 'styled-components';
import { DropdownTypes } from 'components/Atoms/Dropdown';

type StyledDropdownTypes = Pick<DropdownTypes, 'indicatorStyle'>;

const StyledDropdown = styled.details<StyledDropdownTypes>`
  position: relative;

  menu {
    position: absolute;
    right: 0;
    top: 45px;
    z-index: 99;
    overflow: hidden;
  }

  ${({ indicatorStyle }) => {
    // eslint-disable-next-line default-case
    switch (indicatorStyle) {
      case 'FILTERBAR':
        return css`
          &[open] > summary {
            color: ${({ theme }) => theme.colors.body};
            background: ${({ theme }) => theme.colors.line};
          }

          menu {
            left: 0;
          }
        `;

      case 'SIDEBAR':
        return css`
          menu {
            right: 0;
            left: 25px;
            top: 61px;
          }
        `;
    }
  }}

  &[open] > summary::before {
    position: fixed;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    z-index: 80;
    display: block;
    cursor: default;
    content: ' ';
    background: transparent;
  }
`;

export default StyledDropdown;
