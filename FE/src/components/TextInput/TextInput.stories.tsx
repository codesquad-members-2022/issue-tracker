import { ComponentStory, ComponentMeta } from '@storybook/react';
import TextInput from '@/components/TextInput';

export default {
  title: 'Input/TextInput',
  component: TextInput
} as ComponentMeta<typeof TextInput>;

export const Default: ComponentStory<typeof TextInput> = args => {
  return <TextInput {...args}></TextInput>;
};
