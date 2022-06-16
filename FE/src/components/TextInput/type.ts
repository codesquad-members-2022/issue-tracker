import { Status } from '@/constants/message';

type StyleType = 'large' | 'medium' | 'small';

interface IStyleProps {
  status?: Status;
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
  status?: string;
  visible: boolean;
}

interface IStyled_textInput extends IStyleProps {
  styleType?: StyleType;
}

interface TextInputProps extends IStyled_textInput {
  placeholder?: string;
  label?: string;
  maxLength?: number;
}

export type { StyleType, IStyleProps, IStyled_label, IStyled_textInput, TextInputProps };
