import { ComponentStory, ComponentMeta } from '@storybook/react';
import UserProfile from '@/components/UserProfile';

export default {
  title: 'UserProfile/UserProfile',
  component: UserProfile
} as ComponentMeta<typeof UserProfile>;

const Template: ComponentStory<typeof UserProfile> = args => <UserProfile {...args}></UserProfile>;

export const UserProfileStory = Template.bind({});
UserProfileStory.storyName = 'UserProfile';
