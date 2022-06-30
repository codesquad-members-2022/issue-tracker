import { useState, Dispatch, SetStateAction } from 'react';
import { BrowserRouter } from 'react-router-dom';

import PrivateRouter from './private';
import PublicRouter from './public';

export interface HomeTypes {
  isOAuth: boolean;
  setIsOAuth: Dispatch<SetStateAction<boolean>>;
}

const Routers = (): JSX.Element => {
  const hasToken = () => !!window.localStorage.getItem('refresh_token');
  const [isOAuth, setIsOAuth] = useState<boolean>(hasToken());

  return (
    <BrowserRouter basename={process.env.PUBLIC_URL}>
      {isOAuth ? (
        <PrivateRouter isOAuth={isOAuth} setIsOAuth={setIsOAuth} />
      ) : (
        <PublicRouter isOAuth={isOAuth} setIsOAuth={setIsOAuth} />
      )}
    </BrowserRouter>
  );
};

export default Routers;
