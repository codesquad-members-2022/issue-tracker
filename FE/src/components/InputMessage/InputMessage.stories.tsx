import { ComponentStory, ComponentMeta } from '@storybook/react';
import InputMessage from '@/components/InputMessage';

export default {
  title: 'Input/InputMessage',
  component: InputMessage,
  args: { messageType: 'login' }
} as ComponentMeta<typeof InputMessage>;

export const Default: ComponentStory<typeof InputMessage> = args => {
  return <InputMessage {...args}></InputMessage>;
};
