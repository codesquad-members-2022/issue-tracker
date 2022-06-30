import ROUTE_URL from '@/router/routeUrl';
import { isTokenExist } from '@/utils/user';
import { Navigate, Outlet } from 'react-router-dom';
import { useLocation } from 'react-router-dom';

export default function AuthRoute() {
  const { pathname } = useLocation();
  const isLogin = isTokenExist();
  const isLoginPage = pathname === ROUTE_URL.BASE || pathname === `/${ROUTE_URL.LOGIN}`;

  if (!isLogin && !isLoginPage) {
    return <Navigate to={ROUTE_URL.LOGIN} replace />;
  }

  if (isLogin && isLoginPage) {
    return <Navigate to={ROUTE_URL.ISSUE_LIST} replace />;
  }

  return <Outlet />;
}
