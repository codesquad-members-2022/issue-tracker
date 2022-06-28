import { useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import axios from 'axios';

function OAuth() {
  const URI = 'http://louie-03.com/login';

  useEffect(() => {
    const getToken = async () => {
      try {
        const response = await fetch(URI);
        const data = await response.json();
        console.log(data);
      } catch (error) {
        console.log(error);
      }
    };
    getToken();
  });
  return <div>loading.....</div>;
}

export default OAuth;
