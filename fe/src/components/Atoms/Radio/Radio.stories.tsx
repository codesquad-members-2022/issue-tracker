import { ComponentMeta, ComponentStory } from '@storybook/react';
import RadioOption, { TextOptionsTypes } from 'components/Atoms/Radio/';

export default {
  title: 'Molecules/RadioOption',
  component: RadioOption,
} as ComponentMeta<typeof RadioOption>;

const Template: ComponentStory<typeof RadioOption> = (args) => <RadioOption {...args} />;

const textOptions: TextOptionsTypes[] = [
  { id: 1, label: '어두운 색', handleOnClick: () => {} },
  { id: 2, label: '밝은 색', handleOnClick: () => {} },
];

export const RadioOptions = Template.bind({});
RadioOptions.args = { textOptions, title: '글자색 지정' };
