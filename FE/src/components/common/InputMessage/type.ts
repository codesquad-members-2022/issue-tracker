import React from 'react';

type MessageType = 'login';

type Status = 'success' | 'error' | 'lengthCheck' | null;

interface IInputMessageProps {
  children: React.ReactNode;
  status: Status;
}

export type { MessageType, Status, IInputMessageProps };
