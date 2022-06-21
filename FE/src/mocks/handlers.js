import { rest } from 'msw';

// create handlers

// rest.get('url', (req, res, tx))

export const handlers = [
  rest.get('http://localhost:3030/jwttoken', (req, res, ctx) => {
    return res(
      ctx.json({
        access: '1234567891',
        refresh: '1234567891',
      }),
    );
  }),

  rest.get('http://localhost:3030/issues', (req, res, ctx) => {
    return res(
      ctx.json([
        { name: 'Cherries', imagePath: '/images/cherries.png' },
        { name: 'M&Ms', imagePath: '/images/m-and-ms.png' },
        { name: 'Hot fudge', imagePath: '/images/hot-fudge.png' },
      ]),
    );
  }),
];
