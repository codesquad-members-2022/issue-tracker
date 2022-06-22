import { atom } from 'recoil';

export type LabelType = {
  id: number;
  title: string;
  color: string;
  description: string;
};
type LabelStateType = {
  key: string;
  default: LabelType;
};
const initialState: LabelStateType = {
  key: 'labelState',
  default: {
    id: 1,
    title: '라벨 이름',
    color: '#007AFF',
    description: '설명',
  },
};
export const labelState = atom(initialState);
