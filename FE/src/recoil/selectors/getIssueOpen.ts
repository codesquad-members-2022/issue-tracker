import { selector } from 'recoil';
import { issueOpenState } from '../atoms/issueOpen';

export const getIssueOpen = selector({
  key: 'getIssueOpen',
  get: ({ get }) => {
    const issueOpen = get(issueOpenState);

    return issueOpen;
  },
  set: ({ set }, newValue) => {
    if (typeof newValue === 'boolean') {
      set(issueOpenState, newValue);
      sessionStorage.setItem('openIssue', `${newValue}`);
    }
  },
});
