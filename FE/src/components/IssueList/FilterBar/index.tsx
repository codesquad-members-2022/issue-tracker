import { useEffect, useState } from 'react';
import { COLOR } from '@/styles/common';
import { $FilterBar, $InputWrapper } from '@/components/IssueList/FilterBar/style';
import { IFilterBarProps } from '@/components/IssueList/FilterBar/type';
import useInputTextValue from '@/hooks/useInputTextValue';
import { Icon } from '@/components/common/Icon';
import TextInput from '@/components/common/TextInput';
import Dropdown from '@/components/common/Dropdown';
import { useFilterCondition, useFilterConditionDispatch } from '@/contexts/FilterCondition';
import {
  createFilterConditionString,
  InitFilterCondition
} from '@/contexts/FilterCondition/reducer';

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
  const filterConditionContext = useFilterCondition();
  const dispatch = useFilterConditionDispatch();

  useEffect(() => {
    const filterConditionString = createFilterConditionString(filterConditionContext);
    updateInputValue('search', filterConditionString, TEXT_INPUT_DEBOUNCE_TIME);
  }, [filterConditionContext]);

  return (
    <$FilterBar isFocus={isFocus}>
      <Dropdown
        left="left"
        indicatorGap="30px"
        indicatorPadding="0 24px"
        indicatorName={indicatorName}
        panelName={panelName}
        options={options}
        initialValue={initialValue}
      />
      <$InputWrapper onFocus={() => setIsFocus(true)} onBlur={() => setIsFocus(false)}>
        <Icon iconType="search" color={inputInfo.value !== '' ? COLOR.label : COLOR.placeholder} />
        <TextInput
          status={null}
          width="100%"
          height="36px"
          padding="0 10px"
          borderRadius="0"
          styleType={inputStyleType}
          value={inputInfo.value}
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
