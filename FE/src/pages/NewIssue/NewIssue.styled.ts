import styled, { css } from 'styled-components';
import { BtnBase } from 'common/util.styled';
import { BtnStyles } from 'common/button.styled';
import { TextStyles } from 'common/text.styled';

export const Footer = styled.footer`
  margin-top: 32px;
  border-top: 1px solid ${({ theme: { colors } }) => colors.grey1};
  padding-top: 32px;
  display: flex;
  justify-content: flex-end;
  gap: 32px;
`;

export const Btn = styled.button<{
  size: string;
  fontWeight: string;
  fontSize: string;
  lineHeight: string;
  color: string;
}>`
  ${BtnBase};
  ${BtnStyles}
  ${TextStyles}
  ${({ theme: { colors } }) => css`
    color: ${colors.offWhite};
    background-color: ${colors.primary};
  `}
`;
