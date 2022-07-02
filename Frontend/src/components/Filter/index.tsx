import Icons from '@/components/DropDown/Icons';

import * as S from './style';

interface FilterProps {
  handleMouseOver: () => void;
  handleMouseOut: () => void;
  handleMouseDown: () => void;
  handleMouseUp: () => void;
  dropDownTitle: string;
  isMouseOvered: boolean;
  isActive: boolean;
  handleClick?: () => void;
}

export default function Filter({
  handleMouseOver,
  handleMouseOut,
  handleMouseDown,
  handleMouseUp,
  dropDownTitle,
  isMouseOvered,
  isActive,
  handleClick,
}: FilterProps) {
  return (
    <S.Filter
      onMouseOver={handleMouseOver}
      onMouseOut={handleMouseOut}
      onMouseDown={handleMouseDown}
      onMouseUp={handleMouseUp}
      onClick={handleClick}
    >
      <S.DropDown type="checkbox" />
      <S.DropdownLabel>
        <S.DropDownTitle isMouseOvered={isMouseOvered}>
          {dropDownTitle}
        </S.DropDownTitle>
      </S.DropdownLabel>
      <S.IconContainer>
        <Icons
          type="bottomArrow"
          mode={
            (isActive && 'active') || (isMouseOvered && 'hover') || 'initial'
          }
        />
      </S.IconContainer>
    </S.Filter>
  );
}
