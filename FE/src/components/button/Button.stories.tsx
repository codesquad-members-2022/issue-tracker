import { ComponentStory, ComponentMeta } from '@storybook/react';
import { Button } from '@/components/Button';

export default {
  title: 'Button/Button',
  component: Button
} as ComponentMeta<typeof Button>;

const Template: ComponentStory<typeof Button> = args => <Button {...args}></Button>;

export const Large = Template.bind({});
Large.args = {
  text: 'Button',
  styleType: 'large'
};
Large.storyName = 'Button(Large)';

export const MediumStandard = Template.bind({});
MediumStandard.args = {
  text: 'Button',
  styleType: 'mediumStandard'
};
MediumStandard.storyName = 'Button(Medium-Standard)';

export const SmallStandard = Template.bind({});
SmallStandard.args = {
  text: 'Button',
  styleType: 'smallStandard',
  iconType: 'plus'
};
SmallStandard.storyName = 'Button(Small-Standard)';

export const SmallSecondary = Template.bind({});
SmallSecondary.args = {
  text: 'Button',
  styleType: 'smallSecondary',
  iconType: 'plus'
};
SmallSecondary.storyName = 'Button(Small-Secondary)';

export const MediumText = Template.bind({});
MediumText.args = {
  text: 'Button',
  styleType: 'mediumText',
  iconType: 'plus'
};
MediumText.storyName = 'Button(Medium-Text)';

export const SmallText = Template.bind({});
SmallText.args = {
  text: 'Button',
  styleType: 'smallText',
  iconType: 'plus'
};
SmallText.storyName = 'Button(Small-Text)';
