import { ComponentStory, ComponentMeta } from '@storybook/react';
import { Icon } from '@/components/Icon';
import Label from '@/components/Label';
import { LABEL } from '@/styles/common';

export default {
  title: 'Label/Label',
  component: Label,
  args: { status: 'open' }
} as ComponentMeta<typeof Label>;

export const Default: ComponentStory<typeof Label> = ({ children, ...args }) => {
  return (
    <Label {...args}>
      <Icon IconType="openLabel" color={LABEL[args.status].color} />
      열린 이슈
    </Label>
  );
};
