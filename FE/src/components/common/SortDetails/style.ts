import styled from 'styled-components';

export const SortDetails = styled.details`
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;

  summary {
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 4px;
    min-width: 80px;
  }
`;
