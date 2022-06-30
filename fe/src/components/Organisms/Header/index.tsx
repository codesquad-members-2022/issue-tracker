/* eslint-disable jsx-a11y/mouse-events-have-key-events */
/* eslint-disable camelcase */
import { useState, Dispatch, SetStateAction } from 'react';
import { useNavigate } from 'react-router-dom';

import styled from 'styled-components';
import Logo from 'components/Atoms/Logo';
import UserImage from 'components/Atoms/UserImage';

const FlexDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 27px 0 60px 0;
`;

type LogoutButtonTypes = {
  modal: boolean;
};

const User = styled.div<LogoutButtonTypes>`
  position: relative;

  button {
    position: absolute;
    left: -50%;
    width: 100px;
    padding: 8px 16px;
    background: ${({ theme }) => theme.colors.offWhite};
    border: 1px solid ${({ theme }) => theme.colors.line};
    border-radius: 12px;
    color: ${({ theme }) => theme.colors.label};
    ${({ theme }) => theme.fontStyles.textXSmall};
    cursor: pointer;

    display: ${({ modal }) => (modal ? 'block' : 'none')};
  }
`;

const Header = ({ setIsOAuth }: { setIsOAuth: Dispatch<SetStateAction<boolean>> | undefined }) => {
  const navigate = useNavigate();
  const [logoutModal, setLogoutModal] = useState(false);

  const data = localStorage.getItem('userInfo');
  if (!data) return <div />;

  const { id, loginId, profileImageUrl } = JSON.parse(data);

  const clickHandler = () => {
    localStorage.removeItem('refresh_token');
    localStorage.removeItem('userInfo');
    setIsOAuth?.(false);
    navigate('/');
  };

  return (
    <FlexDiv>
      <Logo logoSize="Medium" />
      <User
        className="user"
        onMouseOver={() => setLogoutModal(true)}
        onMouseLeave={() => setLogoutModal(false)}
        modal={logoutModal}
      >
        <UserImage id={id} profileImageUrl={profileImageUrl} loginId={loginId} imgSize="MEDIUM" />
        <button type="button" onClick={clickHandler}>
          로그아웃
        </button>
      </User>
    </FlexDiv>
  );
};

export default Header;
