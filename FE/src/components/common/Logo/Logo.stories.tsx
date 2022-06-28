import { ComponentStory, ComponentMeta } from '@storybook/react';
import Logo from '@/components/common/Logo';

export default {
  title: 'Logo/Logo',
  component: Logo,
  args: { type: 'large' }
} as ComponentMeta<typeof Logo>;

export const Default: ComponentStory<typeof Logo> = args => {
  return <Logo {...args}></Logo>;
};
