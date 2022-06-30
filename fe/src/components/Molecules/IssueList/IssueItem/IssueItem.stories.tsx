import { ComponentStory, ComponentMeta } from '@storybook/react';
import { colors } from 'styles/theme';
import IssueItem, { IssueItemTypes } from 'components/Molecules/IssueList/IssueItem/';

export default {
  title: 'Molecules/IssueList/Item',
  component: IssueItem,
} as ComponentMeta<typeof IssueItem>;

const Template: ComponentStory<typeof IssueItem> = (args) => <IssueItem {...args} />;

const checkedIssue: string[] = [];

const IssueItemInfo: IssueItemTypes = {
  id: 1,
  title: '이슈 트래커 개발',
  status: 'string',
  labels: [
    {
      id: 0,
      title: 'documentation',
      labelStyle: 'STANDARD',
      titleColor: 'white',
      backgroundColor: colors.primary.blue,
    },
  ],
  createdAt: '2022-06-17 13:30:00',
  milestone: '이슈 트래커',
  writer: {
    id: 0,
    loginId: '도톨',
    profileImageUrl: 'https://avatars.githubusercontent.com/u/92701121?v=4',
  },
  assignees: [
    {
      id: 0,
      loginId: '도톨',
      profileImageUrl: 'https://avatars.githubusercontent.com/u/92701121?v=4',
    },
    {
      id: 1,
      loginId: '도비',
      profileImageUrl: 'https://avatars.githubusercontent.com/u/85747667?s=96&v=4',
    },
    {
      id: 2,
      loginId: '후',
      profileImageUrl: 'https://avatars.githubusercontent.com/u/68011320?v=4',
    },
    {
      id: 3,
      loginId: '제리',
      profileImageUrl: 'https://avatars.githubusercontent.com/u/81368630?v=4',
    },
  ],
  checkedItemHandler: (id: string, isChecked: boolean) => {
    // eslint-disable-next-line no-unused-expressions
    isChecked ? checkedIssue.push(id) : checkedIssue.pop();
  },
  checkedIssue,
};

export const Default = Template.bind({});
Default.args = IssueItemInfo;
