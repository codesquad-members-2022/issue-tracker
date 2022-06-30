import { atom } from 'recoil';

export const milestoneData = atom({
  key: 'milestoneData',
  default: [
    {
      title: '마스터즈 코스',
      description: '코드스쿼드 마스터즈 코스',
      dueDate: '2022-07-01',
    },
    {
      title: 'OAuth 연결',
      description: 'github OAuth2 연결',
      dueDate: '2022-06-28',
    },
    {
      title: '마일스톤 테스트',
      description: '',
      dueDate: '2022-07-30',
    },
    {
      title: '점심 메뉴 고르기',
      description: '추천받아요',
      dueDate: '2022-06-27',
    },
    {
      title: '장마',
      description: '비가 온다',
      dueDate: '2022-07-10',
    },
  ],
});
