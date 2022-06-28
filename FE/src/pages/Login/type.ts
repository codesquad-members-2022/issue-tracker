import { Status } from '@/components/common/InputMessage/type';

type InputName = 'id' | 'pw';

interface LimitLength {
  min: number;
  max: number;
}

interface Error {
  id: { status: Status; message: string };
  pw: { status: Status; message: string };
}

export type { InputName, LimitLength, Error };
