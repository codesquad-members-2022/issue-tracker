import { useRef, useState } from 'react';
import Icon from '@/assets/icons/Icon';
import { GREYSCALE } from '@/constants';
import styled from 'styled-components';

type FilterBarProps = {};

function FilterBar({}: FilterBarProps) {
  const [value, setValue] = useState('is:issue is:open');
  const [isFocus, setFocus] = useState(false);

  const iconColor = isFocus ? GREYSCALE.LABEL : GREYSCALE.PLACEHOLDER;

  const onChange = (event) => setValue(event.target.value);

  return (
    <FilterBarBox>
      <ButtonBox>
        필터 <Icon iconName="chevronDown" stroke={iconColor} />
      </ButtonBox>
      <InputBox>
        <Icon iconName="search" stroke={iconColor} />
        <Input
          type="text"
          placeholder="Search all issues"
          value={value}
          onChange={onChange}
        />
      </InputBox>
    </FilterBarBox>
  );
}

const FilterBarBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'center', 'center')}
  width:600px;
  border-radius: 12px;
  overflow: hidden;
  background-color: ${GREYSCALE.INPUT_BACKGROUND};
  border: 1px solid ${GREYSCALE.LINE};
`;

const ButtonBox = styled.button`
  ${({ theme }) =>
    theme.LAYOUT.flexLayoutMixin('row', 'space-between', 'center')}
  padding:6px 24px;
  width: 128px;
  height: 40px;
  color: ${GREYSCALE.LABEL};
  background-color: ${GREYSCALE.BACKGROUND};
  border-right: 1px solid ${GREYSCALE.LINE};
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL};

  &:hover {
    color: ${GREYSCALE.BODY};
    background-color: ${GREYSCALE.LINE};
  }
`;

const InputBox = styled.div`
  position: relative;

  svg {
    position: absolute;
    top: 12px;
    left: 24px;
  }
`;

const Input = styled.input`
  width: 472px;
  height: 40px;
  padding: 6px 24px 6px 48px;
  background-color: ${GREYSCALE.INPUT_BACKGROUND};
  color: ${GREYSCALE.TITLE_ACTION};
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_SMALL}

  ::placeholder {
    color: ${GREYSCALE.PLACEHOLDER};
  }

  :focus {
    background-color: ${GREYSCALE.OFF_WHITE};
  }
`;

export default FilterBar;
