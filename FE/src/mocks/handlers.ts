import { rest } from 'msw';
import allIssueList from './allIssueList';

const handlers = [
  rest.get('/test', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(allIssueList));
  })
];

export default handlers;
