import { atom } from 'recoil';

export const assigneeList = atom({
  key: 'assigneeList',
  default: [
    {
      name: 'dott',
      profileImage:
        'https://ca.slack-edge.com/T74H5245A-U02RA7K501L-3c55992367ca-512',
    },
    {
      name: 'ver',
      profileImage:
        'https://ca.slack-edge.com/T74H5245A-U02RZEM30AX-d54a4bce1cd4-512',
    },
  ],
});
