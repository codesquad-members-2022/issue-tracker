import { ComponentStory, ComponentMeta } from '@storybook/react';
import DropDown from '@/components/DropDown';

export default {
  title: 'DropDown/DropDown',
  component: DropDown
} as ComponentMeta<typeof DropDown>;

const Template: ComponentStory<typeof DropDown> = args => <DropDown {...args}></DropDown>;

export const Large = Template.bind({});
Large.args = {
  title: 'Filter'
};
Large.storyName = 'DropDown';
