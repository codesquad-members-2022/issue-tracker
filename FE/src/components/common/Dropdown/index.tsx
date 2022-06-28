import { useState, useRef, useEffect } from 'react';
import Button from '@/components/common/Button';
import { Icon } from '@/components/common/Icon';
import Panel from '@/components/common/Dropdown/Panel';
import { IDropDown } from '@/components/common/Dropdown/type';
import { $DropDown } from '@/components/common/Dropdown/style';

export default function DropDown({
  indicatorName,
  indicatorGap,
  indicatorPadding,
  panelName,
  initialValue,
  ...panelProps
}: IDropDown) {
  const [isOpen, setIsOpen] = useState(false);
  const [selectedValue, setSelectedValue] = useState(initialValue);
  const panelRef = useRef<HTMLDivElement>(null);

  const updateSelectedValue = (value: string) => {
    setSelectedValue(value);
  };

  const focusOnPanel = () => {
    if (isOpen) panelRef?.current?.focus();
  };

  useEffect(focusOnPanel, [isOpen]);

  return (
    <$DropDown>
      <Button
        styleType="mediumText"
        gap={indicatorGap}
        padding={indicatorPadding}
        onClick={() => setIsOpen(!isOpen)}
      >
        {indicatorName}
        <Icon iconType={isOpen ? 'arrowUp' : 'arrowDown'} />
      </Button>
      {isOpen && (
        <Panel
          {...panelProps}
          panelRef={panelRef}
          title={panelName}
          selectedValue={selectedValue}
          updateSelectedValue={updateSelectedValue}
          hidePanel={() => setIsOpen(false)}
        />
      )}
    </$DropDown>
  );
}
