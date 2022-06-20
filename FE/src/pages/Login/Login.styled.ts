import styled from 'styled-components';
import { Link } from 'react-router-dom';

import { BtnStyles, CustomBtn } from '../../common/button.styled';
import { TextStyles } from '../../common/text.styled';
import { FlexCenterSorter, InputBase } from '../../common/util.styled';

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: ${({ theme: { colors } }) => colors.background};
`;

export const Wrapper = styled.main`
  ${FlexCenterSorter}
  flex-direction: column;
`;

export const Logo = styled.h1`
  ${FlexCenterSorter}
`;

export const LogoImg = styled.img`
  display: block;
`;

export const GitHubBtn = styled.a<{ size: string }>`
  ${FlexCenterSorter};
  ${BtnStyles}
  margin-top: 61px;
  color: ${({ theme: { colors } }) => colors.offWhite};
  background-color: ${({ theme: { colors } }) => colors.titleActive};
`;

export const OR = styled.p`
  ${TextStyles}
  margin: 24px 0;
  text-align: center;
`;

export const IdInput = styled.input`
  ${InputBase}
`;

export const PasswordInput = styled.input`
  ${InputBase}
  margin-top: 16px;
`;

export const IdBtn = styled(CustomBtn)`
  margin-top: 24px;
`;

export const MemberBtn = styled(Link)`
  ${TextStyles}
  margin-top: 30px;
`;
