import { atom } from 'recoil';

interface loginStateType {
  key: string;
  default: boolean;
}

const initialState: loginStateType = {
  key: 'loginState',
  default: true,
};

export const loginState = atom(initialState);
