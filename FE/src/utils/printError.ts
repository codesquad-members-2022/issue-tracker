import { AxiosError } from 'axios';

const printError = (error: AxiosError) => {
  console.error(error.message);
};

export default printError;
