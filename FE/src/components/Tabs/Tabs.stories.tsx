import { ComponentStory, ComponentMeta } from '@storybook/react';
import Tabs from '@/components/Tabs';

export default {
  title: 'Tabs/Tabs',
  component: Tabs
} as ComponentMeta<typeof Tabs>;

const Template: ComponentStory<typeof Tabs> = args => <Tabs {...args}></Tabs>;

export const Tab = Template.bind({});
Tab.args = {
  list: [
    {
      name: '레이블',
      iconType: 'label',
      count: 100
    },
    {
      name: '마일스톤',
      iconType: 'milestone',
      count: 10
    }
  ]
};
Tab.storyName = 'Tabs';
