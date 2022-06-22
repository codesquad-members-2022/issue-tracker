import { useState } from 'react';
import {
  $Panel,
  $PanelTitle,
  $Select,
  $SelectList,
  $SelectedItem
} from '@/components/Dropdown/Panel/style';
import { IPanelProps } from '@/components/Dropdown/Panel/type';

export default function Panel({
  panelRef,
  title,
  options,
  selected,
  handlePanelBlur,
  ...props
}: IPanelProps) {
  const [selectedValue, setSelectedValue] = useState<string | undefined>(selected);

  return (
    <$Panel ref={panelRef} tabIndex={-1} onBlur={handlePanelBlur} {...props}>
      <$PanelTitle>{title}</$PanelTitle>
      <$Select value={selectedValue} onChange={({ target }) => setSelectedValue(target.value)}>
        {options.map(({ value }) => (
          <option key={value} value={value}></option>
        ))}
      </$Select>
      <$SelectList>
        {options.map(({ children, radio, value }) => (
          <$SelectedItem
            key={value}
            id={value}
            selected={value === selectedValue}
            onClick={() => setSelectedValue(value)}
          >
            {children}
            {value === selectedValue ? radio?.on : radio?.off}
          </$SelectedItem>
        ))}
      </$SelectList>
    </$Panel>
  );
}
