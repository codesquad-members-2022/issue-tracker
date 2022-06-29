import { useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';

const URI = 'http://louie-03.com/login/callback';

function Callback() {
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    const getToken = async () => {
      const code = searchParams.get('code');

      console.log('코드', code);
      const baseURL = `${URI}?code=${code}`;

      // const data = await axios.get(baseURL);
      // console.log(data);
      console.log(baseURL);
    };
    getToken();
  });

  return <div>loading.....</div>;
}

export default Callback;
