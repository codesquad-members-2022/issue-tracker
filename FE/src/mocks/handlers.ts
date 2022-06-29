import { rest } from 'msw';
import issueFilterOptions from './issueFilterOptions';
import issueListData from './issueListData';

const handlers = [
  rest.get('/issues', (req, res, ctx) => {
    const status = req.url.searchParams.get('status');

    return res(ctx.status(200), ctx.json(issueListData[status]));
  }),

  rest.get('/api/issue/labels', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(issueFilterOptions.labels));
  }),

  rest.get('/api/issue/milestones', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(issueFilterOptions.milestones));
  }),

  rest.get('/api/issue/members', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(issueFilterOptions.members));
  }),

  rest.get('/api/issue/authors', (req, res, ctx) => {
    return res(ctx.status(200), ctx.json(issueFilterOptions.members));
  })
];

export default handlers;
