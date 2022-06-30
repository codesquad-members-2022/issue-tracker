import { ButtonTypes } from 'components/Atoms/Button';
import { colors } from 'styles/theme';

interface subNavButtonStyleTypes {
  add: ButtonTypes;
  close: ButtonTypes;
}

export const SUB_NAV_BUTTON_STYLES: subNavButtonStyleTypes = {
  add: {
    buttonStyle: 'STANDARD',
    iconInfo: {
      fill: colors.offWhite,
      icon: 'Plus',
      stroke: colors.offWhite,
    },
    label: '추가',
    size: 'SMALL',
  },
  close: {
    buttonStyle: 'SECONDARY',
    iconInfo: {
      icon: 'XSquare',
    },
    label: '닫기',
    size: 'SMALL',
  },
};

interface commonFormTypes {
  add: ButtonTypes;
  confirm: {
    add: ButtonTypes;
    cancel: ButtonTypes;
  };
}

export const COMMON_FORM_BUTTON: commonFormTypes = {
  add: {
    buttonStyle: 'STANDARD',
    iconInfo: {
      fill: colors.offWhite,
      icon: 'Plus',
      stroke: colors.offWhite,
    },
    label: '완료',
    size: 'SMALL',
  },
  confirm: {
    add: {
      buttonStyle: 'STANDARD',
      iconInfo: {
        icon: 'Edit',
        stroke: colors.offWhite,
      },
      label: '완료',
      size: 'SMALL',
    },
    cancel: {
      buttonStyle: 'SECONDARY',
      iconInfo: {
        icon: 'XSquare',
      },
      label: '취소',
      size: 'SMALL',
    },
  },
};

interface LabelItemButtonTypes {
  edit: ButtonTypes;
  delete: ButtonTypes;
}

export const LABEL_ITEM_BUTTON: LabelItemButtonTypes = {
  edit: {
    buttonStyle: 'NO_BORDER',
    iconInfo: { icon: 'Edit' },
    label: '편집',
    size: 'SMALL',
  },
  delete: {
    buttonStyle: 'NO_BORDER',
    iconInfo: { icon: 'Trash' },
    label: '삭제',
    size: 'SMALL',
  },
};
