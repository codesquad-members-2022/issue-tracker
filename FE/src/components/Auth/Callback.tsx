import React from 'react';
import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import qs from 'qs';
import { useQuery } from 'react-query';

const Callback = () => {
  const authUri = `BE와협의한 주소`;
  const { search } = useLocation();
  const navigate = useNavigate();

  useEffect(() => {
    const { code } = qs.parse(search, {
      ignoreQueryPrefix: true,
    });
    console.log(code);
    navigate('/issues');

    // const getToken = async () => {
    //   const { code } = qs.parse(location.search, {
    //     ignoreQueryPrefix: true,
    //   });

    //   try {
    //     const response = await fetch(`${authUri}?code=${code}`);
    //     const data = await response.json();

    //     localStorage.setItem('token', data.jwt);
    //     localStorage.setItem('ProfileURL', data.avatar_url);

    //     history.push('/');
    //   } catch (error) {}
    // };

    // getToken();
  }, []);

  return <div>123</div>;
};

export default Callback;
