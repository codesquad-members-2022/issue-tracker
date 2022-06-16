type MessageType = 'login';

type Status = 'success' | 'error';

type MassageText = Record<Status, string>;

const MESSAGE: Record<MessageType, MassageText> = {
  login: {
    success: '사용 가능한 아이디 입니다.',
    error: '이미 사용하고 있는 아이디 입니다.'
  }
};

export type { MessageType, Status };
export { MESSAGE };
