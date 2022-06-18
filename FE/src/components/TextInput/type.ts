import { Status } from '@/components/InputMessage/type';
import { StyledComponent } from 'styled-components';

type StyleType = 'large' | 'medium' | 'small';

interface IStyleProps {
  status?: Status;
  width?: string;
  height?: string;
  color?: string;
  background?: string;
  border?: string;
  borderRadius?: string;
}

interface IStyled_label {
  styleType?: StyleType;
  status?: string;
  visible: boolean;
}

interface IStyled_textInput extends IStyleProps {
  styleType?: StyleType;
  visibleLabel: boolean;
}

interface ITextInputProps extends IStyleProps {
  styleType?: StyleType;
  placeholder?: string;
  label?: string;
  type?: string;
  name: string;
  as?: StyledComponent<'div', any, IStyled_textInput, never>;
  handleChange?: Function;
}

export type { StyleType, IStyleProps, IStyled_label, IStyled_textInput, ITextInputProps };
