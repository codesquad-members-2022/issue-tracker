import { ComponentStory, ComponentMeta } from '@storybook/react';
import Layout from '@/layout';

export default {
  title: 'Layout/Layout',
  component: Layout
} as ComponentMeta<typeof Layout>;

const Template: ComponentStory<typeof Layout> = args => <Layout {...args}></Layout>;

export const Large = Template.bind({});
Large.args = {};
Large.storyName = 'Layout(Large)';
