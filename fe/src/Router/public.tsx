import { Route, Routes } from 'react-router-dom';
import { Home, Login, Oauth, SignUp } from 'pages';
import { HomeTypes } from 'Router';

const PublicRouter = ({ isOAuth, setIsOAuth }: HomeTypes) => {
  return (
    <Routes>
      <Route path="/" element={<Home isOAuth={isOAuth} setIsOAuth={setIsOAuth} />}>
        <Route index element={<Login />} />
        <Route path="/auth" element={<Oauth setIsOAuth={setIsOAuth} />} />
        <Route path="/sign-up" element={<SignUp setIsOAuth={setIsOAuth} />} />
      </Route>
      <Route path="*" element={<Login />} />
    </Routes>
  );
};

export default PublicRouter;
