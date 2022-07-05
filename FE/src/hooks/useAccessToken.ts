/* eslint-disable @typescript-eslint/no-explicit-any */
/* eslint-disable @typescript-eslint/no-unused-vars */
import { useState, useEffect } from 'react';
import axios from 'axios';

const URI = 'http://louie-03.com/reissue/access-token';

export function useAccessToken() {
  const [isTrigger, setIsTrigger] = useState(false);
  const [fetchStatus, setFetchStatus] = useState(200);

  useEffect(() => {
    const accessToken = localStorage.getItem('accessToken') as string;
    const refreshToken = localStorage.getItem('refreshToken') as string;

    refreshAccessToken(accessToken, refreshToken);
  }, []);

  const refreshAccessToken = async (
    accessToken: string,
    refreshToken: string,
  ) => {
    try {
      const newAccessToken = (await axios.get(URI, {
        headers: { 'Access-Token': accessToken, 'Refresh-Token': refreshToken },
      })) as string;

      localStorage.setItem('accessToken', newAccessToken);
    } catch (error: any) {
      const {
        response: { status },
      } = error;

      switch (status) {
        case 401:
          setFetchStatus(401);
          break;
        case 403:
          setFetchStatus(403);
          break;
        default:
          setFetchStatus(status);
          break;
      }
    }
  };

  return { fetchStatus, setIsTrigger };
}
