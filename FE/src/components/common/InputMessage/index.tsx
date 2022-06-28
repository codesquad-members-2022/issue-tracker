import { $Message } from '@/components/common/InputMessage/style';
import { IInputMessageProps } from '@/components/common/InputMessage/type';

export default function InputMessage({ children, status }: IInputMessageProps) {
  return <$Message status={status}>{children}</$Message>;
}
