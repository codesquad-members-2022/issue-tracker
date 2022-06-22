import { atom } from 'recoil';
import userImageURL from 'assets/images/UserImageLarge.svg';
import userImageURL2 from 'assets/images/UserImageLarge2.svg';

const initialData = {
  id: 2,
  title: 'FE 이슈트래커 디자인 시스템 구현',
  writer: {
    id: 1,
    name: 'Oni',
    imageUrl: userImageURL,
  },
  writtenTime: '2022-06-17T16:37:04',
  milestone: '',
  labels: [
    {
      id: 1,
      title: 'documentation',
      description: '서비스에 대한 개선 사항 혹은 추가 사항',
      color: 'blue2',
    },
  ],
  assignees: [
    {
      id: 1,
      name: 'Oni',
      imageUrl: userImageURL,
    },
    {
      id: 2,
      name: 'Daniel',
      imageUrl: userImageURL2,
    },
  ],
  closed: false,
};

export const issueState = atom({
  key: 'issue2',
  default: initialData,
});
