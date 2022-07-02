import styled from 'styled-components';

export const Container = styled.div`
  width: 1280px;
  margin: 0 auto;
`;

export const MenuBar = styled.div`
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'space-between')};
  margin: 0 0 24px 0;
`;

export const Title = styled.h1``;

export const UserInfo = styled.img``;

export const TapContainer = styled.div<{
  isMouseOvered: boolean;
  isActive: boolean;
}>`
  ${({ theme }) => theme.mixins.flexBox('row', 'start', 'start')};
  border-radius: 0px 11px 11px 0px;
  background-color: 'red';
  margin: 0 16px 0 0;
`;

export const Menus = styled.div`
  display: flex;
`;
