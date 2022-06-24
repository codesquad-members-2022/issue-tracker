import { useEffect } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';

function Callback() {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const authUri = 'BE와 협의한 주소';

  useEffect(() => {
    const getToken = async () => {
      const code = searchParams.get('code');

      try {
        const response = await fetch(`${authUri}?code=${code}`);
        const data = await response.json();

        localStorage.setItem('token', data.jwt);
        localStorage.setItem('userImageUrl', data);

        navigate('/');
      } catch (error) {
        throw new Error();
      }
    };
    getToken();
  }, [searchParams, navigate, authUri]);

  return <div>Callback</div>;
}
export default Callback;
