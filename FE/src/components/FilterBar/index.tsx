import { $FilterBar, $InputWrapper } from '@/components/FilterBar/style';
import { Icon } from '@/components/Icon';
import TextInput from '@/components/TextInput';
import { IStyleProps } from '@/components/TextInput/type';
import { IButtonStyleProps } from '@/components/Button/type';
import useInputTextValue from '@/hooks/useInputTextValue';
import { COLOR } from '@/styles/common';
import { useState } from 'react';

const focusStyle: IStyleProps = {
  status: null,
  border: 'none'
};

const buttonHoverStyle: IButtonStyleProps = {
  background: COLOR.line
};

const TEXT_INPUT_DEBOUNCE_TIME = 0;
const TEXT_INPUT_INIT_VALUE = 'is:issue is:open';

type InputName = 'search';

const dropDownOption = [
  {
    children: '열린 이슈',
    radio: {
      off: <Icon iconType="radioOff" />,
      on: <Icon iconType="radioOn" />
    },
    value: 'a'
  },
  {
    children: '내가 작성한 이슈',
    radio: {
      off: <Icon iconType="radioOff" />,
      on: <Icon iconType="radioOn" />
    },
    value: 'b'
  },
  {
    children: '나에게 할당된 이슈',
    radio: {
      off: <Icon iconType="radioOff" />,
      on: <Icon iconType="radioOn" />
    },
    value: 'c'
  },
  {
    children: '내가 댓글을 남긴 이슈',
    radio: {
      off: <Icon iconType="radioOff" />,
      on: <Icon iconType="radioOn" />
    },
    value: 'd'
  },
  {
    children: '닫힌 이슈',
    radio: {
      off: <Icon iconType="radioOff" />,
      on: <Icon iconType="radioOn" />
    },
    value: 'e'
  }
];

export default function FilterBar() {
  const { inputInfo, updateInputValue } = useInputTextValue<InputName>(TEXT_INPUT_INIT_VALUE);
  const [isFocus, setIsFocus] = useState(false);

  return (
    <$FilterBar isFocus={isFocus}>
      {/* <DropDown
        indicatorName="Filter"
        left="right"
        options={dropDownOption}
        panelName="이슈 필터"
        selected="a"
        top="0px"
        panelRef={undefined}
        title={''}
      /> */}
      <$InputWrapper onFocus={() => setIsFocus(true)} onBlur={() => setIsFocus(false)}>
        <Icon iconType="search" color={inputInfo.value != '' ? COLOR.label : COLOR.placeholder} />
        <TextInput
          status={null}
          styleType="small"
          placeholder="Search all issues"
          name="search"
          borderRadius="0px"
          focusStyle={focusStyle}
          width="400px"
          defaultValue={TEXT_INPUT_INIT_VALUE}
          handleChange={({ name, value }: { name: InputName; value: string }) =>
            updateInputValue(name, value, TEXT_INPUT_DEBOUNCE_TIME)
          }
        />
      </$InputWrapper>
    </$FilterBar>
  );
}
