type StyleType = 'large' | 'medium' | 'small';

type Status = 'right' | 'error';

interface IStyleProps {
  width?: string;
  height?: string;
  color?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
  visibleLabel: boolean;
}

interface IStyled_label {
  styleType?: StyleType;
  visible: boolean;
}

interface IStyled_textInput extends IStyleProps {
  styleType?: StyleType;
}

export type { StyleType, Status, IStyleProps, IStyled_label, IStyled_textInput };
