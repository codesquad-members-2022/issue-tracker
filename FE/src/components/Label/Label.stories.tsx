import { ComponentStory, ComponentMeta } from '@storybook/react';
import { Icon } from '@/components/Icon';
import Label from '@/components/Label';
import { Status } from '@/components/Label/type';
import { LABEL } from '@/styles/common';

export default {
  title: 'Label/Label',
  component: Label,
  args: { status: 'open' }
} as ComponentMeta<typeof Label>;

export const Default: ComponentStory<typeof Label> = ({ children, ...args }) => {
  const getIcon = (status: Status) => {
    switch (status) {
      case 'open':
        return <Icon iconType="openLabel" color={LABEL[status].color} />;
      case 'close':
        return <Icon iconType="closeLabel" color={LABEL[status].color} />;
      default:
        return null;
    }
  };

  return (
    <Label {...args}>
      {getIcon(args.status)}
      열린 이슈
    </Label>
  );
};
