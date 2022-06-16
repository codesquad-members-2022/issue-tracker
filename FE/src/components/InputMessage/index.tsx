import { Styled_message } from '@/components/InputMessage/style';
import { IInputMessageProps } from '@/components/InputMessage/type';
import { MESSAGE } from '@/constants/message';

export default function InputMessage({ messageType, status }: IInputMessageProps) {
  return <Styled_message status={status}>{MESSAGE[messageType][status]}</Styled_message>;
}
