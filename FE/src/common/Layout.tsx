import Icon from '@/assets/icons/Icon';
import { GREYSCALE } from '@/constants';
import { Outlet } from 'react-router-dom';
import styled from 'styled-components';

function Layout() {
  return (
    <LayoutBox>
      <Header>
        <Logo>Issue Tracker</Logo>
        <Icon iconName="userImageLarge" width={44} height={44} />
      </Header>
      <Outlet />
    </LayoutBox>
  );
}

const LayoutBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('column', 'center', 'center')}
`;
const Header = styled.div`
  width: 1280px;
  height: 95px;
  ${({ theme }) =>
    theme.LAYOUT.flexLayoutMixin('row', 'space-between', 'center')}
`;

const Logo = styled.h1`
  ${({ theme }) => theme.TYPOGRAPHY.LOGOTYPE_REGULAR}
  color: ${GREYSCALE.TITLE_ACTION};
`;

export default Layout;
