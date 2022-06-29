import { useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';

const URI = 'http://louie-03.com/login/callback';

function Callback() {
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    const getToken = async () => {
      const code = searchParams.get('code') as string;

      const data = await axios.get(URI, {
        params: { code },
      });
      console.log(data);
    };
    getToken();
  }, []);

  return <div>loading.....</div>;
}

export default Callback;
