import { useState, useRef, useEffect } from 'react';
import Button from '@/components/Button';
import { Icon } from '@/components/Icon';
import Panel from '@/components/Dropdown/Panel';
import { IDropDown } from '@/components/Dropdown/type';
import { $DropDown } from '@/components/Dropdown/style';

export default function DropDown({ indicatorName, panelName, ...panelProps }: IDropDown) {
  const [isOpen, setIsOpen] = useState(false);
  const panelRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    panelRef?.current?.focus();
  }, [isOpen]);

  return (
    <$DropDown>
      <Button styleType="mediumText" onClick={() => setIsOpen(!isOpen)}>
        {indicatorName}
        <Icon iconType={isOpen ? 'arrowUp' : 'arrowDown'} />
      </Button>
      {isOpen && (
        <Panel
          {...panelProps}
          panelRef={panelRef}
          title={panelName}
          handlePanelBlur={() => setIsOpen(!isOpen)}
        />
      )}
    </$DropDown>
  );
}
