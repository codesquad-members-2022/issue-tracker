import { ComponentStory, ComponentMeta } from '@storybook/react';
import NotFound from '@/pages/NotFound';

export default {
  title: 'Pages/NotFound',
  component: NotFound
} as ComponentMeta<typeof NotFound>;

export const Default: ComponentStory<typeof NotFound> = args => {
  return <NotFound pageName="Not Found"></NotFound>;
};
