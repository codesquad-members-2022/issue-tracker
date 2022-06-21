import { atom } from 'recoil';
type LabelType = {
  key: string;
  id: number;
  title: string;
  color: string;
  description: string;
  deleted: boolean;
};
const initialState: LabelType = {
  key: 'labelState',
  id: 1,
  title: 'documentation',
  color: 'blue',
  description: '서비스에 대한 개선 사항 혹은 추가 사항',
  deleted: false,
};
export const LabelState = atom(initialState);
