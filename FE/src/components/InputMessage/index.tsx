import { $Message } from '@/components/InputMessage/style';
import { IInputMessageProps } from '@/components/InputMessage/type';

export default function InputMessage({ children, status }: IInputMessageProps) {
  return <$Message status={status}>{children}</$Message>;
}
