import React, { useEffect } from 'react';
import { QueryClient, useQuery } from 'react-query';
import { useLocation, useNavigate } from 'react-router-dom';
import qs, { ParsedQs } from 'qs';
import LoginLoading from '@components/loginCallback/LoginLoading';
import LoginError from '@components/loginCallback/LoginError';

// // backend url : http://3.34.47.60:8080/auth/github?code=${code}
// // test url : https://test-234b2-default-rtdb.firebaseio.com/.json

export type parsedQueryType =
  | string
  | ParsedQs
  | string[]
  | ParsedQs[]
  | undefined
  | null;

const queryClient = new QueryClient();

// for test
// const fetchAuth = async (code: parsedQueryType) => {
//   const response = await fetch(`http://localhost:3030/jwttoken`);
//   if (!response.ok) {
//     throw new Error('response not ok');
//   }

//   return response.json();
// };

const fetchAuth = async (code: parsedQueryType) => {
  const response = await fetch(
    `http://13.125.219.203:8080/auth/github?code=${code}`,
    // {
    //   headers: {
    //     Accept: 'application/json',
    //   },
    // },
  );
  if (!response.ok) {
    throw new Error('response not ok');
  }

  return response.json();
};

const fetchIssues = async () => {
  const response = await fetch(`http://localhost:3030:/issues`);
  if (!response.ok) {
    throw new Error('response not ok');
  }

  return response.json();
};

// const prefetchIssues = async (token: { access: string; refersh: string }) => {
//   await queryClient.prefetchQuery('issues', () => fetchIssues(token));
// };

const Callback = () => {
  const navigate = useNavigate();
  // const { search } = useLocation();
  // const { code } = qs.parse(search, {
  //   ignoreQueryPrefix: true,
  // });
  useEffect(() => {
    const getToken = async () => {
      const { code } = qs.parse(location.search, {
        ignoreQueryPrefix: true,
      });

      try {
        const response = await fetch(
          `http://13.125.219.203:8080/auth/github?code=${code}`,
        );
        const data = await response.json();

        console.log(data);
      } catch (error) {
        console.log(error);
      }
    };

    getToken();
  }, []);
  // const { status, data } = useQuery(['auth', code], () => fetchAuth(code), {
  //   refetchOnWindowFocus: false,
  //   retry: false,
  // });

  // useEffect(() => {
  //   if (status === 'success') {
  //     // prefetchIssues(data);
  //     console.log(data);
  //     // navigate('/issues');
  //     // useEffect를 사용하지않으면 컴포넌트 렌더링 오류가 발생해서, useEffect 사용
  //     // 해당 내용에대해 더 알아보기
  //   }
  // }, [status]);

  // if (status === 'loading') {
  //   return <LoginLoading />;
  // }

  // if (status === 'error') {
  //   return <LoginError />;
  // }

  return <></>;
};

export default Callback;
