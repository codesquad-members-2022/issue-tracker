import Icon, { IconNameType } from '@/assets/icons/Icon';
import { COLORS, GREYSCALE } from '@/constants';

export type CheckBoxType = 'initial' | 'active' | 'disable';

type CheckBoxProps = {
  checkBoxType: CheckBoxType;
  onClick: () => void;
};

type CheckBoxIconProps = {
  iconName: IconNameType;
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
