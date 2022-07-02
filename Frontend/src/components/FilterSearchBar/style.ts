import styled from 'styled-components';

export const FilterSearchBarContainer = styled.div`
  display: flex;
  align-items: center;
`;

export const SearchBar = styled.input<{ height: string }>`
  width: 472px;
  height: ${({ height }) => height};
  border: 1px solid ${({ theme }) => theme.colors.greyScale.border};
  border-radius: 0px 11px 11px 0px;
`;
