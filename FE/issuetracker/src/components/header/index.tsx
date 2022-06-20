import * as S from 'components/header/styled.index';
import AccountSrc from 'assets/images/UserImageLarge.svg';

function Header() {
  const isLoginPage = window.location.href === `http://localhost:3000/`;
  return (
    <S.HeaderWrap checkPage={isLoginPage}>
      <S.Title to="/issueList">Issue Tracker</S.Title>
      <S.AccountImg src={AccountSrc} />
    </S.HeaderWrap>
  );
}

export default Header;
