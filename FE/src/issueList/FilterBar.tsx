import { useState } from 'react';
import Icon from '@icon';
import { GREYSCALE } from '@constants';
import styled from 'styled-components';
import DropDownPanel from '@components/dropDown/DropDownPanel';

const filterCheckBoxItems = [
  { isChecked: true, label: '열린 이슈' },
  { isChecked: false, label: '내가 작성한 이슈' },
  { isChecked: false, label: '나에게 할당된 이슈' },
  { isChecked: false, label: '내가 댓글을 남긴 이슈' },
  { isChecked: false, label: '닫힌 이슈' }
];

function FilterBar() {
  // NOTE :
  // 드롭다운에서 클릭하면 해당 필터 옵션을 변경 => 필터 옵션이 변경되면 해당 옵션으로 get요청
  // 필터바의 필터옵션 상태로 관리해야할 것 같음
  // 기존에 어떤 옵션들이 선택되었는지를 알 수 있어야 함
  // 이슈 목록에서 담당자, 레이블, 마일스톤, 작성자 옵션을 선택할 수 있는데 이것도 같이 동기화 되야 함

  const [value, setValue] = useState('is:issue is:open');
  const [isFocus, setFocus] = useState(false);
  const [isFilterButtonShow, setFilterButtonShow] = useState(false);

  const bgColor = isFocus ? GREYSCALE.OFF_WHITE : GREYSCALE.BACKGROUND;
  const borderColor = isFocus ? GREYSCALE.TITLE_ACTION : GREYSCALE.LINE;
  const iconColor = isFocus ? GREYSCALE.LABEL : GREYSCALE.PLACEHOLDER;

  const handleFilterBarFocus = () => setFocus(true);
  const handleFilterInputValue = (event: React.ChangeEvent<HTMLInputElement>) =>
    setValue(event.target.value);

  const handleFilterButtonMouseDown = () =>
    setFilterButtonShow(!isFilterButtonShow);

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
      <DropDownBox>
        {isFilterButtonShow && (
          <DropDownPanel
            items={filterCheckBoxItems}
            showCheckBox
            filterName="이슈필터"
          />
        )}
      </DropDownBox>
      <InputBox>
        <Icon iconName="search" stroke={iconColor} />
        <Input
          type="text"
          placeholder="Search all issues"
          value={value}
          onFocus={handleFilterBarFocus}
          onChange={handleFilterInputValue}
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
`;

const DropDownBox = styled.div`
  position: absolute;
  top: 44px;
  left: 0;
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
