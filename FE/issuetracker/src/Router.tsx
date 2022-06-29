import { Navigate, Route, Routes } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import Header from 'components/header';
import IssueList from 'components/IssueList';
import Login from 'components/Login';
import Issue from 'components/Issue';
import NewIssue from 'components/newIssue';
import LabelPage from 'components/LabelPage';
import MileStone from 'components/milestone';
import Loading from 'components/Loading';
import { loginState } from 'store/loginState';

interface Props {
  login: boolean;
  redirectPath: string;
  children: React.ReactNode;
}

function ProtectedRoute({ login, redirectPath = '/login', children }: Props) {
  if (!login) {
    return <Navigate to={redirectPath} replace />;
  }
  return (
    <>
      <Header />
      {children}
    </>
  );
}

function Router() {
  const login = useRecoilValue(loginState);
  return (
    <Routes>
      <Route
        path="/"
        element={
          <ProtectedRoute login={login} redirectPath="/login">
            <IssueList />
          </ProtectedRoute>
        }
      />
      <Route path="/login" element={<Login />} />
      <Route path="/loading" element={<Loading />} />
      <Route
        path="/newIssue"
        element={
          <ProtectedRoute login={login} redirectPath="/login">
            <NewIssue />
          </ProtectedRoute>
        }
      />
      <Route
        path="/issue"
        element={
          <ProtectedRoute login={login} redirectPath="/login">
            <Issue />
          </ProtectedRoute>
        }
      />
      <Route
        path="/label"
        element={
          <ProtectedRoute login={login} redirectPath="/login">
            <LabelPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/milestone"
        element={
          <ProtectedRoute login={login} redirectPath="/login">
            <MileStone />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
}

export default Router;
