import { Outlet } from 'react-router-dom';
import * as S from './Container.styled';

export function Container() {
  return (
    <S.Wrapper>
      <S.Header>
        <S.Logo>
          <S.LogoImg src="./imgs/LogotypeMedium.svg" />
        </S.Logo>
        <S.User>
          <S.UserImg src="./imgs/UserImageLarge.svg" />
        </S.User>
      </S.Header>
      <Outlet />
    </S.Wrapper>
  );
}
