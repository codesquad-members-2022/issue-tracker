import { ComponentStory, ComponentMeta } from '@storybook/react';
import { colors } from 'styles/theme';
import Label from 'components/Atoms/Label';

export default {
  title: 'Atoms/Label',
  component: Label,
} as ComponentMeta<typeof Label>;

const Template: ComponentStory<typeof Label> = (args) => <Label {...args} />;

export const Light = Template.bind({});
Light.args = {
  id: 0,
  title: '레이블 이름',
  labelStyle: 'STANDARD',
  titleColor: 'white',
  backgroundColor: colors.primary.blue,
  description: 'string',
};

export const Dark = Template.bind({});
Dark.args = {
  id: 1,
  title: '레이블 이름',
  labelStyle: 'STANDARD',
  titleColor: 'white',
  backgroundColor: colors.line,
};

export const Line = Template.bind({});
Line.args = {
  id: 2,
  backgroundColor: colors.line,
  title: '작성자',
  labelStyle: 'LINE',
};
