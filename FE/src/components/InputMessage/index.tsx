import { Styled_message } from '@/components/InputMessage/style';
import { IInputMessageProps } from '@/components/InputMessage/type';

export default function InputMessage({ children, status }: IInputMessageProps) {
  return <Styled_message status={status}>{children}</Styled_message>;
}
