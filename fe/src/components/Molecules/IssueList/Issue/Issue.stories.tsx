import { ComponentStory, ComponentMeta } from '@storybook/react';
import Issue, { IssueInfoType } from 'components/Molecules/IssueList/Issue';
import { colors } from 'styles/theme';

export default {
  title: 'Molecules/IssueList/Issue',
  component: Issue,
} as ComponentMeta<typeof Issue>;

const Template: ComponentStory<typeof Issue> = (args) => <Issue {...args} />;

const IssueInfo: IssueInfoType = {
  id: 1,
  title: '이슈 트래커 개발',
  labels: [
    {
      id: 0,
      title: 'documentation',
      labelStyle: 'STANDARD',
      titleColor: 'white',
      backgroundColor: colors.primary.blue,
    },
    {
      id: 1,
      title: 'FE',
      labelStyle: 'STANDARD',
      titleColor: 'black',
      backgroundColor: colors.secondary.lightPurple,
    },
  ],
  writer: {
    id: 1,
    loginId: '도비',
    profileImageUrl: 'https://avatars.githubusercontent.com/u/92701121?v=4',
  },
  createdAt: '2022-06-17 13:30:00',
  milestone: '이슈 트래커',
};

export const Default = Template.bind({});
Default.args = IssueInfo;
