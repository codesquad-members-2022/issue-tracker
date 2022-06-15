import styled from 'styled-components';
import { NavLink } from 'react-router-dom';

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: ${({ theme: { colors } }) => colors.background};
`;

export const Wrapper = styled.main``;

export const Logo = styled.h1``;

export const LogoImg = styled.img.attrs({
  alt: 'logo',
  src: './LogotypeLarge.svg',
})``;

export const GitHubBtn = styled(NavLink)`
  display: block;
  width: 340px;
  height: 64px;
  border-radius: 20px;
  color: ${({ theme: { colors } }) => colors.offWhite};
  background-color: ${({ theme: { colors } }) => colors.titleActive};
  text-align: center;
  line-height: 64px;
`;
