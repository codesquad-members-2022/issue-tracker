import IssueList from '@/pages/IssueList';
import Loading from '@/pages/Loading';
import Login from '@/pages/Login';
import NotFound from '@/pages/NotFound';
import URL from '@/router/routeUrl';

export const routes = [
  { path: URL.base, element: <Login /> },
  { path: URL.login, element: <Login /> },
  { path: URL.loading, element: <Loading /> },
  { path: URL.detailIssue, element: <NotFound pageName="Detail Issue" /> },
  { path: URL.issueList, element: <IssueList /> },
  {
    path: URL.list,
    element: <NotFound pageName="List" />,
    children: [
      { path: URL.label, element: <NotFound pageName="Label List" /> },
      { path: URL.milestone, element: <NotFound pageName="Milestone List" /> }
    ]
  },
  { path: URL.newIssue, element: <NotFound pageName="New Issue" /> },
  { path: URL.notFound, element: <NotFound pageName="Not Found" /> }
];
