import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const sidebar = styled.aside`
  width: 308px;
  height: 504px;
  border-radius: 16px;
  margin-left: 16px;
  background: ${({ theme }) => theme.backgroundColors.gray1};
`;

export const userBar = styled.div`
  height: 216px;
  padding: 32px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-bottom: 0;
  border-radius: 16px 16px 0 0;
`;

export const labelBar = styled.div`
  height: 140px;
  padding: 32px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-bottom: 0;
`;

export const milestoneBar = styled.div`
  height: 148px;
  padding: 32px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-radius: 0 0 16px 16px;
`;

export const barHeader = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  color: ${({ theme }) => theme.fontColors.gray2};
`;

export const barHeaderText = styled.span`
  ${({ theme }) => theme.fontStyles.linkSmall};
`;

export const userUnit = styled.div`
  ${mixin.flexbox({ vertical: 'center' })};
  height: 44px;
  margin-top: 24px;
`;

export const userName = styled.span`
  ${({ theme }) => theme.fontStyles.textSmall};
`;
