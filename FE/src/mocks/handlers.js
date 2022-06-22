import { rest } from 'msw';

// create handlers

// rest.get('url', (req, res, tx))

export const handlers = [
  rest.get('http://localhost:3030/jwttoken', (req, res, ctx) => {
    return res(
      ctx.json({
        jwt: '1234567891',
      }),
    );
  }),

  rest.get('http://localhost:3030/issues', (req, res, ctx) => {
    return res(
      ctx.json({
        content: [
          {
            memberDto: {
              memberId: 'mockuser',
              avatarUrl: 'https://avatars.githubusercontent.com/u/81129309?v=4',
            },
            issueNumber: 1,
            title: 'mock issue title',
            milestoneTitle: 'mock milestone title',
            createdAt: '2022-06-22T01:27:03.059137',
          },
          {
            memberDto: {
              memberId: 'mockuser',
              avatarUrl: 'https://avatars.githubusercontent.com/u/81129309?v=4',
            },
            issueNumber: 2,
            title: '2번째',
            milestoneTitle: 'mock milestone title',
            createdAt: '2022-06-21T01:27:03.059137',
          },
        ],
      }),
    );
  }),
];
