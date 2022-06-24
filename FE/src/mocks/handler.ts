import { rest } from 'msw';
import { IssueDatasType } from '../pages/Issues/Constants';

const GET_MAIN = rest.get<IssueDatasType>('/issues', (req, res, ctx) => {
  return res(
    ctx.json([
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
      {
        id: 3,
        title: 'msw 사용해보기',
        author: 'Jinnie',
        timeStamp: '1일',
        label: 'feat',
        mileStone: '[FE]Week2',
      },
    ]),
  );
});

export const handlers = [GET_MAIN];

// export const getFruits = rest.get('/issues', (req, res, ctx) =>
//   res(ctx.json(['test', '성공'])),
// );
