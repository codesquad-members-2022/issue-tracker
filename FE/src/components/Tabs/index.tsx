import Button from '../Button';
import { COLOR } from '@/styles/common';
import { $Tab, $TabItem } from '@/components/Tabs/style';
import { listItem, ITabs } from '@/components/Tabs/type';
import { IButtonProps, IButtonStyleProps } from '@/components/Button/type';
import { IconTypes } from '@/components/Icon';

const hoverStyle: IButtonStyleProps = {
  background: COLOR.inputBackground,
  color: COLOR.label
};

const activeStyle: IButtonStyleProps = {
  background: COLOR.line,
  color: COLOR.body
};

const getButtonProps = (iconType?: IconTypes): IButtonProps => {
  return {
    styleType: 'mediumText',
    iconType: iconType,
    width: '100%',
    height: '100%',
    hoverStyle: hoverStyle,
    activeStyle: activeStyle
  };
};

const createButtonText = ({ name, count }: listItem) => `
    ${name} ${count !== undefined ? `(${count})` : ``}
`;

export default function Tabs({ list }: ITabs) {
  return (
    <$Tab>
      {list?.map(({ name, iconType, count }) => (
        <$TabItem key={name}>
          <Button {...getButtonProps(iconType)}>{createButtonText({ name, count })}</Button>
        </$TabItem>
      ))}
    </$Tab>
  );
}
