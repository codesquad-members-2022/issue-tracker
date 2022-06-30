import IssueList from '@/pages/IssueList';
import Loading from '@/pages/Loading';
import Login from '@/pages/Login';
import NotFound from '@/pages/NotFound';
import ROUTE_URL from '@/router/routeUrl';
import AuthRoute from '@/router/AuthRoute';

const {
  BASE,
  LOGIN,
  ISSUE_LIST,
  NOT_FOUND,
  NEW_ISSUE,
  DETAIL_ISSUE,
  LIST,
  LABEL,
  MILESTONE,
  LOADING
} = ROUTE_URL;

export const routes = [
  {
    element: <AuthRoute />,
    children: [
      { path: BASE, element: <Login /> },
      { path: LOGIN, element: <Login /> },
      { path: DETAIL_ISSUE, element: <NotFound pageName="Detail Issue" /> },
      { path: ISSUE_LIST, element: <IssueList /> },
      {
        path: LIST,
        element: <NotFound pageName="List" />,
        children: [
          { path: LABEL, element: <NotFound pageName="Label List" /> },
          { path: MILESTONE, element: <NotFound pageName="Milestone List" /> }
        ]
      },
      { path: NEW_ISSUE, element: <NotFound pageName="New Issue" /> },
      { path: NOT_FOUND, element: <NotFound pageName="Not Found" /> }
    ]
  },
  { path: LOADING, element: <Loading /> }
];
