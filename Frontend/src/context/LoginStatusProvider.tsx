import React, {
  createContext,
  SetStateAction,
  useMemo,
  useState,
  Dispatch,
} from 'react';

type LoginStatus = {
  status: boolean;
  profileUrl: string;
};

type LoginStatusContextType = {
  loginStatus: LoginStatus;
  setLoginStatus: Dispatch<SetStateAction<LoginStatus>>;
};

export const LoginStatusContext = createContext<LoginStatusContextType>({
  loginStatus: {
    status: false,
    profileUrl: '',
  },
  setLoginStatus: () => {},
});

export function LoginStatusProvider({
  children,
}: {
  children: JSX.Element | JSX.Element[];
}) {
  const [loginStatus, setLoginStatus] = useState<LoginStatus>({
    status: false,
    profileUrl: '',
  });
  const loginStatusContext = useMemo(
    () => ({ loginStatus, setLoginStatus }),
    [loginStatus]
  );

  return (
    <LoginStatusContext.Provider value={loginStatusContext}>
      {children}
    </LoginStatusContext.Provider>
  );
}
