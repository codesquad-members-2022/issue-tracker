import uuid from 'react-uuid';
import axios from 'axios';

const getRandomKey = () => uuid();

const fetchDataUseAxios = async (URL: string) => {
  const { data } = await axios.get(`${URL}`);
  return data;
};

export { getRandomKey, fetchDataUseAxios };
