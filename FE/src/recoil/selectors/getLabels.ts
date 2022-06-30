import { selector } from 'recoil';

export const getLabels = selector({
  key: 'GetLabels',
  get: async () => {
    const response = await fetch('/labels');
    const data = await response.json();
    return data;
  },
});
