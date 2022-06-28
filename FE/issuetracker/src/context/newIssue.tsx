import { atom } from 'recoil';
import AccountSrc from 'assets/images/UserImageLarge.svg';
import { MileStoneType, LabelType, AccountType } from 'data';

type WriterType = {
  name: string;
  imgUrl: string;
};
type CommentType = {
  writer: WriterType;
  writtenTime: string;
  description: string;
};

type newIssue = {
  title: string;
  writer: WriterType;
  writtenTime: string;
  comments: Array<CommentType>;
  mileStone: MileStoneType;
  labels: LabelType;
  assignees: AccountType;
};

const initialState: newIssue = {
  title: 'FE 이슈트래커 디자인 시스템 구현',
  writer: {
    name: 'Oni',
    imgUrl: AccountSrc,
  },
  writtenTime: '2022-06-22T16:37:04',
  comments: [
    {
      writer: {
        name: 'Oni',
        imgUrl: AccountSrc,
      },
      writtenTime: '2022-06-22T16:37:04',
      description:
        '처음부터 전부 구현하려고 하지 말고 필수적인 기능부터 만든 후, 차근차근 완성도를 높여보세요',
    },
    {
      writer: {
        name: 'Daniel',
        imgUrl: userImageURL2,
      },
      writtenTime: '2022-06-22T16:37:04',
      description: '마감일은 언제인가요??',
    },
  ],
  milestone: {
    title: '이슈트래커 1주차',
    description: '개발 1주차',
    dueDate: '2022-06-19',
    progress: 0.6666666666666666,
    openedIssue: 2,
    closedIssue: 1,
  },
  labels: [
    {
      title: 'documentation',
      description: '서비스에 대한 개선 사항 혹은 추가 사항',
      color: {
        font: 'gray1',
        background: 'blue2',
      },
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
