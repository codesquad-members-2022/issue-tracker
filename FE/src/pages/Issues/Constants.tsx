export const filterList = {
  title: '이슈필터',
  menus: [
    '열린이슈',
    '내가작성한이슈',
    '나에게 할당된 이슈',
    '내가 댓글을 남긴 이슈',
    '닫힌 이슈',
  ],
};

export const author = {
  title: '담당자 필터',
  menus: ['담당자가 없는 이슈', 'Jinnie', 'Benny', 'Hanse', 'Louise'],
};

export const label = {
  title: '레이블 필터',
  menus: ['bug', 'documentation'],
};

export const mileStone = {
  title: '마일스톤 필터',
  menus: ['마일스톤이없는필터', '마스터즈 코스'],
};

export const writer = {
  title: '작성자 필터',
  menus: ['Jinnie', 'Benny', 'Hanse', 'Louise'],
};

export interface IssueDatasType {
  id: number;
  title: string;
  author: string;
  timeStamp: string;
  label: string;
  mileStone: string;
}
