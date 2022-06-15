import { ComponentStory, ComponentMeta } from '@storybook/react';
import Button from '@/components/button';

export default {
  title: 'Button/Button222',
  component: Button
} as ComponentMeta<typeof Button>;

const Template: ComponentStory<typeof Button> = args => <Button {...args}></Button>;

export const shortButton = Template.bind({});
shortButton.args = {
  width: '100px'
};
shortButton.storyName = 'Button(short)';

export const longButton = Template.bind({});
longButton.args = {
  width: '200px'
};
longButton.storyName = 'Button(long)';

export const Default: ComponentStory<typeof Button> = args => {
  return <Button {...args}></Button>;
};
