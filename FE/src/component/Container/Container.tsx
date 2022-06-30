import { useState, useEffect } from 'react';
import { Outlet } from 'react-router-dom';
import * as S from './Container.styled';

export function Container() {
  const [userImg, setUserImg] = useState('./imgs/UserImageLarge.svg');

  useEffect(() => {
    if (localStorage.getItem('avatarImageUrl'))
      setUserImg(localStorage.getItem('avatarImageUrl') as string);
  }, [userImg]);

  return (
    <S.Wrapper>
      <S.Header>
        <S.Logo>
          <S.LogoImg src="./imgs/LogotypeMedium.svg" />
        </S.Logo>
        <S.User>
          <S.UserImg src={userImg} />
        </S.User>
      </S.Header>
      <Outlet />
    </S.Wrapper>
  );
}
