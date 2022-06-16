import { ComponentStory, ComponentMeta } from '@storybook/react';
import Login from '@/pages/Login';

export default {
  title: 'Pages/Login',
  component: Login
} as ComponentMeta<typeof Login>;

export const Default: ComponentStory<typeof Login> = args => {
  return <Login></Login>;
};
