import { ComponentStory, ComponentMeta } from '@storybook/react';
import UserProfile from '@/components/UserProfile';

export default {
  title: 'UserProfile/UserProfile',
  component: UserProfile,
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
} as ComponentMeta<typeof UserProfile>;

const Template: ComponentStory<typeof UserProfile> = args => <UserProfile {...args}></UserProfile>;

export const UserProfileStory = Template.bind({});
UserProfileStory.storyName = 'UserProfile';
