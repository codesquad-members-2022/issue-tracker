/* eslint-disable storybook/story-exports */
import { ComponentStory, ComponentMeta } from '@storybook/react';
import SideBar from 'components/Molecules/SideBar/';

export default {
  title: 'Molecules/SideBar',
  component: SideBar,
} as ComponentMeta<typeof SideBar>;

const Template: ComponentStory<typeof SideBar> = (args) => <SideBar {...args} />;

const totalAssigneeList = [
  { id: 1, loginId: '담당자가 없는 이슈' },
  {
    id: 2,
    loginId: 'dobby',
    profileImageUrl: 'https://avatars.githubusercontent.com/u/85747667?s=96&v=4',
  },
  {
    id: 3,
    loginId: 'dotori',
    profileImageUrl: 'https://avatars.githubusercontent.com/u/92701121?v=4',
  },
  {
    id: 4,
    loginId: 'jerry',
    profileImageUrl:
      'https://post-phinf.pstatic.net/MjAyMTA2MDRfOTAg/MDAxNjIyNzcyMjY1NzQ2.sRxvXF_CKk6NqfiAI6624veOffmu7GDJmXuoMcmgQv0g.ZA1bWEuLFT7--CvzUFZ6TXN2TWX6rhze_t7ilqwk-tcg.JPEG/IMG_3115.jpg?type=w1200',
  },
  {
    id: 5,
    loginId: 'who-hoo',
    profileImageUrl:
      'https://avatars.githubusercontent.com/u/68011320?s=88&u=c57bfb94cd0919fce142ce2fda6424d3daf54cb8&v=4',
  },
];

const assignees = [
  {
    id: 1,
    loginId: 'jerry',
    profileImageUrl:
      'https://post-phinf.pstatic.net/MjAyMTA2MDRfOTAg/MDAxNjIyNzcyMjY1NzQ2.sRxvXF_CKk6NqfiAI6624veOffmu7GDJmXuoMcmgQv0g.ZA1bWEuLFT7--CvzUFZ6TXN2TWX6rhze_t7ilqwk-tcg.JPEG/IMG_3115.jpg?type=w1200',
  },
  {
    id: 2,
    loginId: 'who-hoo',
    profileImageUrl:
      'https://avatars.githubusercontent.com/u/68011320?s=88&u=c57bfb94cd0919fce142ce2fda6424d3daf54cb8&v=4',
  },
];

const totalMilestoneList = [
  { id: 1, title: '마일스톤이 없는 필터' },
  { id: 2, title: '1주차 마일스톤 BE' },
  { id: 3, title: '1주차 마일스톤 FE' },
];

const milestones = [
  {
    id: 1,
    title: '1주차 마일스톤 BE',
    openIssueCount: 3,
    closedIssueCount: 5,
  },
  {
    id: 2,
    title: '1주차 마일스톤 FE',
    openIssueCount: 3,
    closedIssueCount: 6,
  },
];

const totalLabelList = [
  { id: 1, title: '레이블이 없는 이슈', backgroundColor: '#000000', titleColor: 'black' },
  { id: 2, title: 'bug', backgroundColor: '#B9062F', titleColor: 'black' },
  { id: 3, title: 'documentation', backgroundColor: '#FFFFFF', titleColor: 'black' },
  {
    id: 4,
    title: 'duplicate',
    backgroundColor: '#000000',
    titleColor: 'black',
  },
];

const labels = [
  {
    id: 1,
    title: 'bug',
    backgroundColor: '#B9062F',
    titleColor: 'black',
  },
  {
    id: 2,
    title: 'documentation',
    backgroundColor: '#FFFFFF',
    titleColor: 'black',
  },
  {
    id: 3,
    title: 'duplicate',
    backgroundColor: '#000000',
    titleColor: 'black',
  },
];

const sideBarStandardList = [
  {
    id: 1,
    type: 'ASSIGN',
    indicatorLabel: '담당자',
    dropdownList: totalAssigneeList,
    contentList: [],
  },
  {
    id: 2,
    type: 'LABEL',
    indicatorLabel: '레이블',
    dropdownList: totalLabelList,
    contentList: [],
  },
  { id: 3, type: 'MILESTONE', indicatorLabel: '마일스톤', dropdownList: totalMilestoneList, contentList: [] },
];

const sideBarFilledList = [
  {
    id: 1,
    type: 'ASSIGN',
    indicatorLabel: '담당자',
    dropdownList: totalAssigneeList,
    contentList: assignees,
  },
  {
    id: 2,
    type: 'LABEL',
    indicatorLabel: '레이블',
    dropdownList: totalLabelList,
    contentList: labels,
  },
  { id: 3, type: 'MILESTONE', indicatorLabel: '마일스톤', dropdownList: totalMilestoneList, contentList: milestones },
];

// export const Standard = Template.bind({});
// Standard.args = {
//   sideBarList: sideBarStandardList,
// };

// export const Filled = Template.bind({});
// Filled.args = {
//   sideBarList: sideBarFilledList,
// };
