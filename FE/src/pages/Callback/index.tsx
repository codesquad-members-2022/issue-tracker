/* eslint-disable no-console */
import { useEffect, useState } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';
import axios from 'axios';

const URI = 'http://louie-03.com/login/callback';

function Callback() {
  const [isLoading, setIsLoading] = useState(false);
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (!isLoading) getToken();

    if (localStorage.getItem('accessToken')) navigate('/issues');
  }, [isLoading]);

  const getToken = async () => {
    try {
      const code = searchParams.get('code');

      const { data } = await axios.get(URI, {
        params: { code },
      });

      localStorage.setItem('accessToken', data.accessToken);
      localStorage.setItem('refreshToken', data.refreshToken);
      localStorage.setItem('avatarImageUrl', data.avatarImageUrl);
    } catch (error) {
      console.log(error);
    } finally {
      setIsLoading(true);
    }
  };

  return <div>loading.....</div>;
}

export default Callback;
