import { useState } from 'react';
import { COLOR } from '@/styles/common';
import { $FilterBar, $InputWrapper } from '@/components/FilterBar/style';
import { IFilterBarProps } from '@/components/FilterBar/type';
import useInputTextValue from '@/hooks/useInputTextValue';
import { Icon } from '@/components/Icon';
import TextInput from '@/components/TextInput';
import Dropdown from '@/components/Dropdown';

const TEXT_INPUT_DEBOUNCE_TIME = 0;
const TEXT_INPUT_INIT_VALUE = 'is:issue is:open';

const focusStyle = {
  status: null,
  border: 'none',
  padding: '0 10px'
};

type InputName = 'search';

export default function FilterBar({
  indicatorName,
  panelName,
  options,
  initialValue,
  inputStyleType,
  ...inputProps
}: IFilterBarProps) {
  const { inputInfo, updateInputValue } = useInputTextValue<InputName>(TEXT_INPUT_INIT_VALUE);
  const [isFocus, setIsFocus] = useState(false);

  return (
    <$FilterBar isFocus={isFocus}>
      <Dropdown
        left="left"
        indicatorName={indicatorName}
        panelName={panelName}
        options={options}
        initialValue={initialValue}
      />
      <$InputWrapper onFocus={() => setIsFocus(true)} onBlur={() => setIsFocus(false)}>
        <Icon iconType="search" color={inputInfo.value !== '' ? COLOR.label : COLOR.placeholder} />
        <TextInput
          status={null}
          padding="0 10px"
          borderRadius="0"
          styleType={inputStyleType}
          {...inputProps}
          focusStyle={focusStyle}
          handleChange={({ name, value }: { name: InputName; value: string }) =>
            updateInputValue(name, value, TEXT_INPUT_DEBOUNCE_TIME)
          }
        />
      </$InputWrapper>
    </$FilterBar>
  );
}
