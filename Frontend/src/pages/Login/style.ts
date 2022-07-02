import { Link } from 'react-router-dom';
import styled from 'styled-components';

export const Title = styled.h1`
  font-style: italic;
  font-weight: 400;
  font-size: 56px;
  line-height: 72px;
  color: ${({ theme }) => theme.colors.greyScale.titleActive};
  margin-bottom: 61px;
`;

const ButtonSize = `
  width: 340px;
  height: 64px;
`;

export const Content = styled.p`
  font-weight: 700;
  font-size: 16px;
  color: ${({ theme }) => theme.colors.greyScale.placeholder};
  margin: 24px 0;
`;

export const LoginContainer = styled.div`
  ${({ theme: { mixins } }) => `
		${mixins.flexBox('column', 'center', 'center')};
		width: 100%;
		height: 100%;
	`}
  .disable-true {
    cursor: none;
    pointer-events: none;
  }
`;

export const LoginInput = styled.input`
  ${ButtonSize};
  padding: 0 24px;
  background: ${({ theme }) => theme.colors.greyScale.inputBackground};
  border-radius: 16px;
  &:not(:last-child) {
    margin-bottom: 16px;
  }
  font-weight: 400;
  font-size: 16px;
  line-height: 28px;
  color: ${({ theme }) => theme.colors.greyScale.placeholder};
`;

export const LoginLink = styled(Link)`
  ${ButtonSize};
  border-radius: 20px;
  margin: 24px 0 30px 0;
  text-decoration: none;
`;
