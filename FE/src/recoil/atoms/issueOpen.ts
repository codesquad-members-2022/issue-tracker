import { atom } from 'recoil';

export const issueOpenState = atom({
  key: 'issueListState',
  default: sessionStorage.getItem('openIssue')
    ? sessionStorage.getItem('openIssue') === 'true'
    : true,
});
