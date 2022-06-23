import { ComponentStory, ComponentMeta } from '@storybook/react';
import IssueList from '@/pages/IssueList';

export default {
  title: 'Pages/IssueList',
  component: IssueList
} as ComponentMeta<typeof IssueList>;

export const Default: ComponentStory<typeof IssueList> = args => {
  return <IssueList></IssueList>;
};
