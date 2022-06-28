import { rest } from 'msw';
import issueListData from './issueListData';

const handlers = [
  rest.get('/issues', (req, res, ctx) => {
    const status = req.url.searchParams.get('status');

    return res(ctx.status(200), ctx.json(issueListData[status]));
  })
];

export default handlers;
