import { selector } from 'recoil';

export const getAssginees = selector({
  key: 'getAssginees',
  get: async () => {
    const response = await fetch('/assignees');
    const data = await response.json();
    return data;
  },
});
