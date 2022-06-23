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
  selectedValue,
  updateSelectedValue,
  hidePanel,
  ...props
}: IPanelProps) {
  const handleSelectedItemClick = (value: string) => {
    updateSelectedValue(value);
    hidePanel();
  };

  return (
    <$Panel ref={panelRef} tabIndex={-1} onBlur={hidePanel} {...props}>
      <$PanelTitle>{title}</$PanelTitle>
      <$Select value={selectedValue} onChange={({ target }) => updateSelectedValue(target.value)}>
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
            onMouseUp={() => handleSelectedItemClick(value)}
          >
            {children}
            {value === selectedValue ? radio?.on : radio?.off}
          </$SelectedItem>
        ))}
      </$SelectList>
    </$Panel>
  );
}
