import { ComponentStory, ComponentMeta } from '@storybook/react';
import DropDown from '@/components/Dropdown';
import { Icon } from '@/components/Icon';

export default {
  title: 'DropDown/DropDown',
  component: DropDown
} as ComponentMeta<typeof DropDown>;

const Template: ComponentStory<typeof DropDown> = args => <DropDown {...args}></DropDown>;

export const Large = Template.bind({});
Large.args = {
  indicatorName: 'Filter',
  panelName: '필터 이름',
  options: [
    {
      children: '선택된 필터',
      radio: { on: <Icon iconType="radioOn" />, off: <Icon iconType="radioOff" /> },
      value: 'a'
    },
    {
      children: '선택되지 않은 필터',
      radio: { on: <Icon iconType="radioOn" />, off: <Icon iconType="radioOff" /> },
      value: 'b'
    },
    {
      children: '선택되지 않은 필터',
      radio: { on: <Icon iconType="radioOn" />, off: <Icon iconType="radioOff" /> },
      value: 'c'
    }
  ],
  selected: 'a',
  top: '0px',
  left: 'right'
};
Large.storyName = 'DropDown';
