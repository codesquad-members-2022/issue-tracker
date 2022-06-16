import { ComponentStory, ComponentMeta } from '@storybook/react';
import InputMessage from '@/components/InputMessage';

export default {
  title: 'Input/InputMessage',
  component: InputMessage,
  args: { messageType: 'login', status: 'success' }
} as ComponentMeta<typeof InputMessage>;

export const Default: ComponentStory<typeof InputMessage> = args => {
  return <InputMessage {...args}></InputMessage>;
};
