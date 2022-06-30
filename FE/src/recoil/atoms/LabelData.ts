import { atom } from 'recoil';

export const labelData = atom({
  key: 'labelData',
  default: [
    {
      name: 'FE',
      description: '프론트엔드 이슈',
      color: '#004D23',
      textColor: 'offwhite',
    },
    {
      name: 'BE',
      description: '백엔드 이슈',
      color: '#34C759',
      textColor: 'offwhite',
    },
    {
      name: 'feat',
      description: '새로운 기능 개발',
      color: '#FF3830',
      textColor: 'offwhite',
    },
  ],
});
