import styled from 'styled-components';
import { CustomBtn } from '../../common/button.styled';

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

export const GitHubBtn = styled(CustomBtn)``;
