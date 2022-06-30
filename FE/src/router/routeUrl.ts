type PageType =
  | 'BASE'
  | 'LOGIN'
  | 'ISSUE_LIST'
  | 'NOT_FOUND'
  | 'NEW_ISSUE'
  | 'DETAIL_ISSUE'
  | 'LIST'
  | 'LABEL'
  | 'MILESTONE'
  | 'LOADING';

type URLType = Record<PageType, string>;

const ROUTE_URL: URLType = {
  BASE: '/',
  LOGIN: 'login',
  ISSUE_LIST: 'issue-list',
  NOT_FOUND: '*',
  NEW_ISSUE: 'new-issue',
  DETAIL_ISSUE: 'detail-issue',
  LIST: 'list',
  LABEL: 'label',
  MILESTONE: 'milestone',
  LOADING: '/github-callback'
};

export default ROUTE_URL;
