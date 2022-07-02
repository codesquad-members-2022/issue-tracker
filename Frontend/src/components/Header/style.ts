import { Link } from 'react-router-dom';
import styled from 'styled-components';

export const Container = styled.div`
  width: 1280px;
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'space-between')}
  margin: 0 auto;
  padding-top: 20px;
  padding-bottom: 60px;

  a {
    text-decoration: none;
  }
`;

export const Title = styled.h1`
  font-style: italic;
  font-weight: 500;
  font-size: 32px;
  line-height: 40px;
  color: ${({ theme }) => theme.colors.greyScale.titleActive};
`;

export const LogoLink = styled(Link)`
  text-decoration: none;
  &:focus,
  &:hover,
  &:visited,
  &:link,
  &:active {
    text-decoration: none;
  }
`;
