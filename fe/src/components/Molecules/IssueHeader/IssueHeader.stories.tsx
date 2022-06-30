import { ComponentStory, ComponentMeta } from '@storybook/react';
import IssueHeader from 'components/Molecules/IssueHeader';

export default {
  title: 'Molecules/IssueHeader',
  component: IssueHeader,
} as ComponentMeta<typeof IssueHeader>;

const Template: ComponentStory<typeof IssueHeader> = (args) => <IssueHeader {...args} />;

export const Open = Template.bind({});
Open.args = {
  id: 1,
  title: '이슈1',
  status: 'OPEN',
  isEditer: true,
  writer: {
    id: 1,
    loginId: 'who-hoo',
    profileImageUrl:
      'https://post-phinf.pstatic.net/MjAyMTA2MDRfOTAg/MDAxNjIyNzcyMjY1NzQ2.sRxvXF_CKk6NqfiAI6624veOffmu7GDJmXuoMcmgQv0g.ZA1bWEuLFT7--CvzUFZ6TXN2TWX6rhze_t7ilqwk-tcg.JPEG/IMG_3115.jpg?type=w1200',
  },
  createTime: '2022-06-26T15:41:03.730526',
  commentCount: 3,
};

export const Close = Template.bind({});
Close.args = {
  ...Open.args,
  status: 'CLOSED',
};

export const NoWriter = Template.bind({});
NoWriter.args = {
  ...Open.args,
  isEditer: false,
};
