import * as S from 'components/header/styled.index';
import AccountSrc from 'assets/images/UserImageLarge.svg';

function Header() {
<<<<<<< HEAD
  const isLoginPage: boolean = window.location.href === `http://localhost:3000/`;
=======
  const isLoginPage = window.location.href === `http://localhost:3000/` ? true : false;
>>>>>>> 5061995 (Feat : new Issue 구현중)
  return (
    <S.HeaderWrap checkPage={isLoginPage}>
      <S.Title to="/">Issue Tracker</S.Title>
      <S.AccountImg src={AccountSrc} />
    </S.HeaderWrap>
  );
}

export default Header;
