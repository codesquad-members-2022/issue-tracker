import { ComponentStory, ComponentMeta } from '@storybook/react';
import Panel from '@/components/common/Dropdown/Panel';

export default {
  title: 'Dropdown/Panel',
  component: Panel,
  args: {
    title: '필터 이름',
    options: [
      {
        children: '선택된 필터',
        value: 'a'
      },
      {
        children: '선택되지 않은 필터',
        value: 'b'
      },
      {
        children: '선택되지 않은 필터',
        value: 'c'
      }
    ],
    selected: 'a',
    top: '0px'
  }
} as ComponentMeta<typeof Panel>;

export const Default: ComponentStory<typeof Panel> = ({ ...args }) => {
  return <Panel {...args} />;
};
