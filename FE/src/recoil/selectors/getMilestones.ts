import { selector } from 'recoil';

export const getMilestones = selector({
  key: 'GetMilstones',
  get: async () => {
    const response = await fetch('/milestones');
    const data = await response.json();
    return data;
  },
});
