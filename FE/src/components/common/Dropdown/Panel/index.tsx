import { useState } from 'react';
import {
  $Panel,
  $PanelTitle,
  $Select,
  $SelectList,
  $SelectedItem
} from '@/components/common/Dropdown/Panel/style';
import { IFilterCondition } from '@/types/common';
import { IPanelProps } from '@/components/common/Dropdown/Panel/type';
import { setCondition } from '@/contexts/FilterCondition/action';
import { useFilterConditionDispatch } from '@/contexts/FilterCondition';

export default function Panel({
  panelRef,
  title,
  options,
  selectedValue,
  updateSelectedValue,
  hidePanel,
  ...props
}: IPanelProps) {
  const dispatch = useFilterConditionDispatch();

  const handleSelectedItemClick = (value: string, filterCondition?: IFilterCondition) => {
    updateSelectedValue(value);
    hidePanel();

    if (filterCondition) {
      setCondition(dispatch, { ...filterCondition });
    }
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
        {options.map(({ children, radio, value, filterCondition }) => (
          <$SelectedItem
            key={value}
            id={value}
            selected={value === selectedValue}
            onMouseUp={() => handleSelectedItemClick(value, filterCondition)}
          >
            {children}
            {value === selectedValue ? radio?.on : radio?.off}
          </$SelectedItem>
        ))}
      </$SelectList>
    </$Panel>
  );
}
