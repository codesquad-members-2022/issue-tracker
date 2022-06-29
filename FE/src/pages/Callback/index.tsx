import { useEffect, useState } from 'react';
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';

const URI = 'http://louie-03.com/login/callback';

function Callback() {
  const [searchParams] = useSearchParams();
  const [imgLink, setImgLink] = useState('');

  useEffect(() => {
    getToken();
  }, []);

  const getToken = async () => {
    const code = searchParams.get('code') as string;

    const data = await axios.get(URI, {
      params: { code },
    });

    setImgLink(data.data.avatarImageUrl);
  };

  if (imgLink) return <img src={imgLink} alt="img" />;

  return <div>loading.....</div>;
}

export default Callback;
