import { Status } from '@/components/InputMessage/type';

type StyleType = 'large' | 'medium' | 'small';

interface IStyleProps {
  status: Status;
  width?: string;
  height?: string;
  color?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
}

interface I$Label {
  styleType?: StyleType;
  status?: string;
  visible: boolean;
}

interface I$TextInput extends IStyleProps {
  styleType?: StyleType;
  visibleLabel: boolean;
  hoverStyle?: IStyleProps;
  focusStyle?: IStyleProps;
}

interface ITextInputProps<T> extends IStyleProps {
  styleType?: StyleType;
  placeholder?: string;
  label?: string;
  type?: string;
  name: T;
  handleChange?: Function;
  hoverStyle?: IStyleProps;
  focusStyle?: IStyleProps;
  disabledStyle?: IStyleProps;
  defaultValue?: string;
}

export type { StyleType, IStyleProps, I$Label, I$TextInput, ITextInputProps };
