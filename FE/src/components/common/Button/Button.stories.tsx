import { ComponentStory, ComponentMeta } from '@storybook/react';
import Button from '@/components/common/Button';

export default {
  title: 'Button/Button',
  component: Button
} as ComponentMeta<typeof Button>;

const Template: ComponentStory<typeof Button> = args => <Button {...args}>BUTTON</Button>;

export const Large = Template.bind({});
Large.args = {
  styleType: 'large'
};
Large.storyName = 'Button(Large)';

export const MediumStandard = Template.bind({});
MediumStandard.args = {
  styleType: 'mediumStandard'
};
MediumStandard.storyName = 'Button(Medium-Standard)';

export const SmallStandard = Template.bind({});
SmallStandard.args = {
  styleType: 'smallStandard'
};
SmallStandard.storyName = 'Button(Small-Standard)';

export const SmallSecondary = Template.bind({});
SmallSecondary.args = {
  styleType: 'smallSecondary'
};
SmallSecondary.storyName = 'Button(Small-Secondary)';

export const MediumText = Template.bind({});
MediumText.args = {
  styleType: 'mediumText'
};
MediumText.storyName = 'Button(Medium-Text)';

export const SmallText = Template.bind({});
SmallText.args = {
  styleType: 'smallText'
};
SmallText.storyName = 'Button(Small-Text)';
