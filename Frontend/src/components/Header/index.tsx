import { useContext } from 'react';
import { Link } from 'react-router-dom';

import Button from '@/components/Button';
import { LoginStatusContext } from '@/context/LoginStatusProvider';

import user from '../../../public/image/user1.jpeg';
import UserImg from '../UserImg';
import * as S from './style';

export default function Header() {
  const { loginStatus } = useContext(LoginStatusContext);
  return (
    <S.Container>
      <S.LogoLink to={loginStatus.status ? '/main' : '/'}>
        <S.Title>Issue Tracker</S.Title>
      </S.LogoLink>
      {loginStatus.status ? (
        <UserImg img={user} size="medium" alt="user logo" />
      ) : (
        <Link to="/">
          <Button buttonStyle="sort" hasIcon={false} contents="로그인" />
        </Link>
      )}
    </S.Container>
  );
}
