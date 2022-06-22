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
    title: 'documentation',
    color: '#007AFF',
    description: '상태 관리',
  },
};
export const labelState = atom(initialState);
