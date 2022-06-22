export interface ListItem {
  id: number;
  item: string;
}
export const filterItems: ListItem[] = [
  { id: 1, item: '담당자' },
  { id: 2, item: '레이블' },
  { id: 3, item: '마일스톤' },
  { id: 4, item: '작성자' },
];

export interface IssueDatasType {
  id: number;
  title: string;
  author: string;
  timeStamp: string;
  label: string;
  mileStone: string;
}
export const issueDatas: IssueDatasType[] = [
  {
    id: 1,
    title: 'UI완성',
    author: 'Jinnie',
    timeStamp: '3일',
    label: 'style',
    mileStone: '[FE]Week1',
  },
  {
    id: 2,
    title: 'GitHub 로그인',
    author: 'Jinnie,benny',
    timeStamp: '2일',
    label: 'feat',
    mileStone: '[FE]Week2',
  },
];
