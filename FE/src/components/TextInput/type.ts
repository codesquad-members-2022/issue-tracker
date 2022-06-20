import { Status } from '@/components/InputMessage/type';
import { StyledComponent } from 'styled-components';

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
}

interface ITextInputProps<T> extends IStyleProps {
  styleType?: StyleType;
  placeholder?: string;
  label?: string;
  type?: string;
  name: T;
  as?: StyledComponent<'div', any, I$TextInput, never>;
  handleChange?: Function;
}

export type { StyleType, IStyleProps, I$Label, I$TextInput, ITextInputProps };
