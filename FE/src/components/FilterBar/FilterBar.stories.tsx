import { ComponentStory, ComponentMeta } from '@storybook/react';
import FilterBar from '@/components/FilterBar';
import { Icon } from '@/components/Icon';

export default {
  title: 'FilterBar/FilterBar',
  component: FilterBar,
  args: {
    indicatorName: '필터',
    panelName: '이슈 필터',
    options: [
      {
        children: '열린 이슈',
        radio: {
          off: <Icon iconType="radioOff" />,
          on: <Icon iconType="radioOn" />
        },
        value: 'opened'
      },
      {
        children: '내가 작성한 이슈',
        radio: {
          off: <Icon iconType="radioOff" />,
          on: <Icon iconType="radioOn" />
        },
        value: 'written'
      },
      {
        children: '나에게 할당된 이슈',
        radio: {
          off: <Icon iconType="radioOff" />,
          on: <Icon iconType="radioOn" />
        },
        value: 'assigned'
      },
      {
        children: '내가 댓글을 남긴 이슈',
        radio: {
          off: <Icon iconType="radioOff" />,
          on: <Icon iconType="radioOn" />
        },
        value: 'comments'
      },
      {
        children: '닫힌 이슈',
        radio: {
          off: <Icon iconType="radioOff" />,
          on: <Icon iconType="radioOn" />
        },
        value: 'closed'
      }
    ],
    initialValue: 'opened',
    inputStyleType: 'small',
    placeholder: 'is:issue is:open',
    name: 'issueFilter'
  }
} as ComponentMeta<typeof FilterBar>;

export const Default: ComponentStory<typeof FilterBar> = ({ ...args }) => {
  return <FilterBar {...args} />;
};
