import React, { useEffect } from "react";

const Callback = ({ setIsLogin }) => {
  const authUrl = `http://52.79.76.11/oauth/login`;

  useEffect(() => {
    const getToken = async () => {
      const url = new URL(window.location.href);
      const code = url.searchParams.get("code");

      try {
        const response = await fetch(`${authUrl}?code=${code}`);
        const data = await response.json();

        localStorage.setItem("token", data.jwt);
        setIsLogin(true);
      } catch (error) {
        console.log(error);
      }
    };

    getToken();
    window.location.href = "http://localhost:3000";
  }, [authUrl]);

  return <p>로그인 하는 중...</p>;
};

export default Callback;
