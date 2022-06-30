import { selector } from 'recoil';

export const getIssue = selector({
  key: 'GetIssue',
  get: async () => {
    const response = await fetch('/issue');
    const data = await response.json();
    return data;
  },
});
