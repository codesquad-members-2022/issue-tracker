/* eslint-disable no-unused-vars */
import Icons from '@/components/DropDown/Icons';
import { StyledTap } from '@/components/Tap/style';
import useMouse from '@/hooks/useMouse';

interface TapProps {
  iconType: 'label' | 'alert' | 'search' | 'bottomArrow' | 'mileStone';
  title: string;
  borderDirection?: 'left' | 'right';
}

export default function Tap({
  iconType,
  title,
  borderDirection = 'left',
}: TapProps) {
  const {
    isMouseOvered,
    isActive,
    handleMouseOver,
    handleMouseOut,
    handleMouseDown,
    handleMouseUp,
  } = useMouse(false);

  return (
    <StyledTap
      type="button"
      onMouseOver={handleMouseOver}
      onMouseOut={handleMouseOut}
      onMouseDown={handleMouseDown}
      onMouseUp={handleMouseUp}
      isMouseOvered={isMouseOvered}
      isActive={isActive}
      borderDirection={borderDirection}
    >
      <Icons
        type={iconType}
        mode={(isActive && 'active') || (isMouseOvered && 'hover') || 'initial'}
      />
      <h1>{title} (0)</h1>
    </StyledTap>
  );
}
