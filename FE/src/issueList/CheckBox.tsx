import Icon from '@/assets/icons/Icon';
import icons from '@/assets/icons/svgs';
import { COLORS, GREYSCALE } from '@/constants';

type CheckBoxProps = {
  checkBoxType: 'initial' | 'active' | 'disable';
  onClick: () => void;
};

type CheckBoxIconProps = {
  iconName: keyof typeof icons;
  stroke: string;
  fill: string;
};

function CheckBox({ checkBoxType, onClick }: CheckBoxProps) {
  const initialCheckBox: CheckBoxIconProps = {
    iconName: 'checkBoxInitial',
    stroke: GREYSCALE.LINE,
    fill: GREYSCALE.OFF_WHITE
  };
  const activeCheckBox: CheckBoxIconProps = {
    iconName: 'checkBoxActive',
    stroke: GREYSCALE.OFF_WHITE,
    fill: COLORS.BLUE
  };
  const disableCheckBox: CheckBoxIconProps = {
    iconName: 'checkBoxDisable',
    stroke: GREYSCALE.OFF_WHITE,
    fill: COLORS.BLUE
  };

  const checkBox: CheckBoxIconProps = (() => {
    switch (checkBoxType) {
      case 'active':
        return activeCheckBox;
      case 'disable':
        return disableCheckBox;
      default:
        return initialCheckBox;
    }
  })();

  return (
    <Icon
      iconName={checkBox.iconName}
      stroke={checkBox.stroke}
      fill={checkBox.fill}
      onClick={onClick}
    />
  );
}

export default CheckBox;
