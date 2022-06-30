import { ComponentStory, ComponentMeta } from '@storybook/react';
import { colors } from 'styles/theme';
import IssueList, { IssueListTypes, FilterTabTypes } from 'components/Molecules/IssueList/';

export default {
  title: 'Molecules/IssueList/List',
  component: IssueList,
} as ComponentMeta<typeof IssueList>;

const Template: ComponentStory<typeof IssueList> = (args) => <IssueList {...args} />;

const filterTabs: FilterTabTypes[] = [
  {
    id: 1,
    dropdownTitle: '담당자 필터',
    dropdownList: [
      {
        id: 1,
        title: '도톨',
        profileImageUrl: 'https://avatars.githubusercontent.com/u/92701121?v=4',
      },
      {
        id: 2,
        title: 'dobby',
        profileImageUrl: 'https://avatars.githubusercontent.com/u/85747667?s=96&v=4',
      },
    ],
    indicatorLabel: '담당자',
  },
  {
    id: 2,
    dropdownTitle: '레이블 필터',
    dropdownList: [
      {
        id: 1,
        title: '선택된 필터',
      },
      {
        id: 2,
        backgroundColor: 'red',
        title: 'bug',
      },
      {
        id: 3,
        title: 'dobby',
        profileImageUrl: 'https://avatars.githubusercontent.com/u/85747667?s=96&v=4',
      },
    ],
    indicatorLabel: '레이블',
  },
  {
    id: 3,
    dropdownTitle: '마일스톤 필터',
    dropdownList: [
      {
        id: 0,
        backgroundColor: 'coral',
        title: 'FE 🌈',
      },
      {
        id: 1,
        backgroundColor: 'red',
        title: 'bug 🐛',
      },
      {
        id: 2,
        backgroundColor: 'skyblue',
        title: 'UI 🎨',
      },
    ],
    indicatorLabel: '마일스톤',
  },
  {
    id: 4,
    dropdownTitle: '작성자 필터',
    dropdownList: [
      {
        id: 1,
        title: '선택된 필터',
      },
      {
        id: 2,
        backgroundColor: 'red',
        title: 'bug',
      },
      {
        id: 3,
        title: 'dobby',
        profileImageUrl: 'https://avatars.githubusercontent.com/u/85747667?s=96&v=4',
      },
    ],
    indicatorLabel: '작성자',
  },
];

const IssueListInfo: IssueListTypes = {
  openIssueCount: 0,
  closedIssueCount: 0,
  issues: {
    content: [
      {
        id: 0,
        status: 'string',
        title: '이슈 트래커 개발',
        labels: [
          {
            id: 0,
            title: 'documentation',
            titleColor: 'white',
            backgroundColor: colors.primary.blue,
            description: 'string',
          },
          {
            id: 1,
            title: 'FE',
            titleColor: 'white',
            backgroundColor: colors.secondary.lightPurple,
            description: 'string',
          },
          {
            id: 2,
            title: 'BE',
            titleColor: 'black',
            backgroundColor: colors.success.green,
            description: 'string',
          },
        ],
        createdAt: '2022-06-20T01:05:45.880Z',
        milestone: '이슈 트래커',
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
        writer: {
          id: 0,
          loginId: '제리',
          profileImageUrl: 'https://avatars.githubusercontent.com/u/81368630?v=4',
        },
      },
      {
        id: 1,
        status: 'string',
        title: 'FE 이슈 트래커 개발',
        labels: [
          {
            id: 0,
            title: 'FE',
            titleColor: 'white',
            backgroundColor: colors.secondary.lightPurple,
            description: 'string',
          },
        ],
        createdAt: '2022-06-20T01:05:45.880Z',
        milestone: 'FE 이슈 트래커',
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
        ],
        writer: {
          id: 0,
          loginId: '도톨',
          profileImageUrl: 'https://avatars.githubusercontent.com/u/92701121?v=4',
        },
      },
    ],
  },
  filterTabs: [],
};

export const Default = Template.bind({});
Default.args = IssueListInfo;

export const FilterTab = Template.bind({});
FilterTab.args = {
  ...IssueListInfo,
  filterTabs,
};
