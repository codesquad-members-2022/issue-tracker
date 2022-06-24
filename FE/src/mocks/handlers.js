import { rest } from 'msw';

const handlers = [
  rest.get('/', (req, res, ctx) => {
    return res(
      ctx.status(200),
      ctx.json({
        test: 'test',
      }),
    );
  }),
];

export default handlers;
