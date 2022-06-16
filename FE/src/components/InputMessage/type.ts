import { MessageType, Status } from '@/constants/message';

interface IInputMessageProps {
  messageType: MessageType;
  status: Status;
}

interface IStyled_message {
  status: Status;
}

export type { IInputMessageProps, IStyled_message };
