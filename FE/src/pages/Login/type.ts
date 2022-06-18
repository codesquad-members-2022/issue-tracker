import { Status } from '@/components/InputMessage/type';

type InputName = 'id' | 'pw';

interface LimitLength {
  min: number;
  max: number;
}

interface Error {
  status: Status;
  message: string;
}

export type { InputName, LimitLength, Error };
