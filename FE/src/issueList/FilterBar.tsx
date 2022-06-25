import { useState } from 'react';
import Icon from '@/assets/icons/Icon';
import { GREYSCALE } from '@/constants';
import styled from 'styled-components';
import DropDownPanel from '@/common/DropDownPanel';

const filterCheckBoxItems = [
  { id: 1, isChecked: true, label: '열린 이슈' },
  { id: 2, isChecked: false, label: '내가 작성한 이슈' },
  { id: 3, isChecked: false, label: '나에게 할당된 이슈' },
  { id: 4, isChecked: false, label: '내가 댓글을 남긴 이슈' },
  { id: 5, isChecked: false, label: '닫힌 이슈' }
];

function FilterBar() {
  const [value, setValue] = useState('is:issue is:open');
  const [isFocus, setFocus] = useState(false);
  const [isFilterButtonShow, setFilterButtonShow] = useState(false);

  const bgColor = isFocus ? GREYSCALE.OFF_WHITE : GREYSCALE.BACKGROUND;
  const borderColor = isFocus ? GREYSCALE.TITLE_ACTION : GREYSCALE.LINE;
  const iconColor = isFocus ? GREYSCALE.LABEL : GREYSCALE.PLACEHOLDER;

  const onFocus = () => setFocus(true);
  const onChange = (event: React.ChangeEvent<HTMLInputElement>) =>
    setValue(event.target.value);

  const handleFilterButtonMouseDown = () =>
    isFilterButtonShow ? setFilterButtonShow(false) : setFilterButtonShow(true);

  const handleFilterButtonBlur = () => setFilterButtonShow(false);

  return (
    <FilterBarBox borderColor={borderColor}>
      <ButtonBox
        bgColor={bgColor}
        onMouseDown={handleFilterButtonMouseDown}
        onBlur={handleFilterButtonBlur}
      >
        필터
        <Icon iconName="chevronDown" stroke={iconColor} />
      </ButtonBox>
      {isFilterButtonShow && (
        <DropDownPanel
          items={filterCheckBoxItems}
          showCheckBox
          filterName="이슈필터"
          positionX={44}
          positionY={0}
        />
      )}
      <InputBox>
        <Icon iconName="search" stroke={iconColor} />
        <Input
          type="text"
          placeholder="Search all issues"
          value={value}
          onFocus={onFocus}
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
  border: 1px solid ${({ borderColor }) => borderColor};
  position: relative;
`;

const ButtonBox = styled.button`
  ${({ theme }) =>
    theme.LAYOUT.flexLayoutMixin('row', 'space-between', 'center')}
  padding:6px 24px;
  width: 128px;
  height: 40px;
  color: ${GREYSCALE.LABEL};
  background-color: ${({ bgColor }) => bgColor};
  border-radius: 12px 0 0 12px;
  border-right: 1px solid ${GREYSCALE.LINE};
  ${({ theme }) => theme.TYPOGRAPHY.LINK_SMALL};
  position: relative;

  &:hover {
    color: ${GREYSCALE.BODY};
    background-color: ${GREYSCALE.LINE};
  }

  & > div {
    position: absolute;
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
  border-radius: 0 12px 12px 0;
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
