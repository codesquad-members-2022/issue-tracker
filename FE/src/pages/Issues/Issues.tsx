import axios from 'axios';
import React, { useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';

function Issues(): JSX.Element {
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    (async () => {
      const code = searchParams.get('code');
      const URI = `https://github.com/login/oauth/access_token?client_id=${process.env.REACT_APP_GITHUB_CLIENT_ID}&client_secret=${process.env.REACT_APP_GITHUB_CLIENT_SECRET}&code=${code}`;
      const token = await axios.post(URI);
      console.log(token.data);

      // const githubAPI = await axios.get(
      //   `https://api.github.com/user?Authorization=${token}`,
      // );

      // console.log(githubAPI);
    })();
  }, []);
  return <div />;
}
export default Issues;
