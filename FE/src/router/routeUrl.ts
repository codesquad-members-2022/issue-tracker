type PageType =
  | 'base'
  | 'login'
  | 'issueList'
  | 'notFound'
  | 'newIssue'
  | 'detailIssue'
  | 'list'
  | 'label'
  | 'milestone';

type URLType = Record<PageType, string>;

const URL: URLType = {
  base: '/',
  login: 'login',
  issueList: 'issue-list',
  notFound: '*',
  newIssue: 'new-issue',
  detailIssue: 'detail-issue',
  list: 'list',
  label: 'label',
  milestone: 'milestone'
};

export default URL;
