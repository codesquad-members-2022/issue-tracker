import styled, { css } from 'styled-components';
import { DropdownIndicatorTypes } from 'components/Atoms/Dropdown/DropdownIndicators';

type StyledDropdownIndicatorTypes = Pick<DropdownIndicatorTypes, 'icon' | 'indicatorStyle' | 'isActive'>;

const DropdownIndicators = styled.summary<StyledDropdownIndicatorTypes>`
  display:flex;
  align-items:center;  
  
  width: fit-content;
  height: fit-content;
  list-style: none;
  
  color: ${({ theme }) => theme.colors.label};
  
  ${({ theme }) => theme.fontStyles.linkSmall};
  
  ${({ theme }) => theme.dropdownIndicatorSize};
  
  &:hover,
    label:hover {
      cursor: pointer;
  }
  
  &::after {
    margin-top: 4px;
    content: ${({ icon }) => `url(${icon});`};

    svg{
      path{
        stroke:red !important;
      }
    }
  }

  
  ${({ indicatorStyle }) => {
    // eslint-disable-next-line default-case
    switch (indicatorStyle) {
      case 'STANDARD':
        return css`
          span {
            margin-right: 8px;
          }
        `;
      case 'FILTERBAR':
        return css`
          border: 1px solid ${({ theme }) => theme.colors.line};
          border-radius: 11px 0px 0px 11px;
          height: 40px;
          padding: 6px 24px;
          background: ${({ theme }) => theme.colors.background};

          span {
            min-width: 56px;
            height: 28px;
            margin-right: 12px;
          }

          &:hover {
            color: ${({ theme }) => theme.colors.body};
            background: ${({ theme }) => theme.colors.line};
          }
        `;
      case 'SIDEBAR':
        return css`
          border: 1px solid ${({ theme }) => theme.colors.line};
          border-radius: 16px;
          padding: 32px;
          span {
            width: 224px;
            height: 28px;
          }
        `;
    }
  }}}

  ${({ isActive }) =>
    isActive &&
    css`
      background: ${({ theme }) => theme.colors.offWhite};
      border: 1px solid ${({ theme }) => theme.colors.titleActive};
      border-right: 1px solid ${({ theme }) => theme.colors.line};
    `}
`;

export default DropdownIndicators;
