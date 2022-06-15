import { ComponentStory, ComponentMeta } from '@storybook/react';
import { Button } from '@/components/Button';
import { COLOR } from '@/styles/common';

export default {
  title: 'Button/Button222',
  component: Button
} as ComponentMeta<typeof Button>;

const Template: ComponentStory<typeof Button> = args => <Button {...args}></Button>;

export const Large = Template.bind({});
Large.args = {
  text: 'Button',
  children: 'test',
  styleType: 'Large'
};
Large.storyName = 'Button(Large)';

export const SmallStandard = Template.bind({});
SmallStandard.args = {
  text: 'Button',
  styleType: 'Small-Standard',
  iconType: 'plus'
};
SmallStandard.storyName = 'Button(Small-Standard)';

export const MediumText = Template.bind({});
MediumText.args = {
  text: 'Button',
  styleType: 'Medium-Text',
  iconType: 'plus'
};
MediumText.storyName = 'Button(Medium-Text)';
