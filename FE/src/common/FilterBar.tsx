import { GREYSCALE } from '@/constants';
import React from 'react';
import styled from 'styled-components';

type FilterBarProps = {};

function FilterBar({}: FilterBarProps) {
  return (
    <FilterBarBox>
      <div>필터</div>
      <input type="text" placeholder="isopen" />
    </FilterBarBox>
  );
}

const FilterBarBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row')}
`;

const InputBox = styled.input`
  width: 340px;
  height: 64px;
  background-color: ${GREYSCALE.INPUT_BACKGROUND};
  border-radius: 16px;
  padding: 18px 24px;
  color: ${GREYSCALE.TITLE_ACTION};
  border: 1px solid ${GREYSCALE.INPUT_BACKGROUND};
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_SMALL}

  ::placeholder {
    color: ${GREYSCALE.PLACEHOLDER};
  }

  :focus {
    background-color: ${GREYSCALE.OFF_WHITE};
    border: 1px solid ${GREYSCALE.TITLE_ACTION};
  }
`;

export default FilterBar;
