import { ComponentMeta, ComponentStory } from '@storybook/react';
import ColorCodeInput, { ColorCodeInputTypes } from '.';

export default {
  title: 'Molecules/ColorCode',
  component: ColorCodeInput,
} as ComponentMeta<typeof ColorCodeInput>;

const Template: ComponentStory<typeof ColorCodeInput> = (args) => <ColorCodeInput {...args} />;

const ColorCodeArgs: ColorCodeInputTypes = {
  colorCode: '007AFF',
  handleOnClick: () => {},
  handleOnChange: (string: string) => {},
};

export const ColorCode = Template.bind({});
ColorCode.args = ColorCodeArgs;
