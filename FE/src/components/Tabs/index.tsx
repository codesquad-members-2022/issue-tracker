import Button from '../Button';
import { COLOR } from '@/styles/common';
import { $Tab, $TabItem } from '@/components/Tabs/style';
import { listItem, ITabs } from '@/components/Tabs/type';
import { IButtonProps, IButtonStyleProps } from '@/components/Button/type';
import { Icon } from '@/components/Icon';

const hoverStyle: IButtonStyleProps = {
  background: COLOR.inputBackground,
  color: COLOR.label
};

const activeStyle: IButtonStyleProps = {
  background: COLOR.line,
  color: COLOR.body
};

const buttonPropsValue: IButtonProps = {
  styleType: 'mediumText',
  width: '100%',
  height: '100%',
  gap: '8px',
  hoverStyle: hoverStyle,
  activeStyle: activeStyle
};

const createButtonText = ({ name, count }: listItem) => `
    ${name} ${count !== undefined ? `(${count})` : ``}
`;

export default function Tabs({ list }: ITabs) {
  return (
    <$Tab>
      {list?.map(({ name, iconType, count }) => (
        <$TabItem key={name}>
          <Button {...buttonPropsValue}>
            {iconType && <Icon iconType={iconType} />}
            {createButtonText({ name, count })}
          </Button>
        </$TabItem>
      ))}
    </$Tab>
  );
}
