/* eslint-disable react/function-component-definition */
/* eslint-disable react/jsx-props-no-spreading */
import { ComponentStory, ComponentMeta } from '@storybook/react';
import { NewIssueForm } from './NewIssueForm';

export default {
  title: 'style/NewIssueForm',
  component: NewIssueForm,
} as ComponentMeta<typeof NewIssueForm>;

const Template: ComponentStory<typeof NewIssueForm> = args => (
  <NewIssueForm {...args} />
);

export const Active = Template.bind({});
Active.args = { isActive: true };

export const Disabled = Template.bind({});
Disabled.args = { isActive: false };
