import { useEffect } from 'react';

function OAuth() {
  const URI = 'http://louie-03.com/login';

  useEffect(() => {
    const getToken = async () => {
      try {
        const response = await fetch(URI);
        const data = await response.json();
        // eslint-disable-next-line no-console
        console.log(data);
      } catch (error) {
        // eslint-disable-next-line no-console
        console.log(error);
      }
    };
    getToken();
  });
  return <div>loading.....</div>;
}

export default OAuth;
