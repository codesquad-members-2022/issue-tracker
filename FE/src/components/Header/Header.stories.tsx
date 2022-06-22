import { ComponentStory, ComponentMeta } from '@storybook/react';
import Header from '@/components/Header';

export default {
  title: 'UserProfile/Header',
  component: Header,
  argTypes: {
    src: {
      options: [
        undefined,
        'https://images.unsplash.com/profile-fb-1620954106-6ea0901e5361.jpg?ixlib=rb-1.2.1&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128',
        'https://avatars.githubusercontent.com/u/17706346?v=4',
        'https://avatars.githubusercontent.com/u/34249911?v=4'
      ],
      control: { type: 'radio' }
    }
  }
} as ComponentMeta<typeof Header>;

const Template: ComponentStory<typeof Header> = args => <Header {...args}></Header>;

export const HeaderStory = Template.bind({});
HeaderStory.storyName = 'Header';
