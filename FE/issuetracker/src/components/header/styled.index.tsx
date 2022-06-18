import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const HeaderWrap = styled.div<{ checkPage: boolean }>`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  display: ${({ checkPage }) => (checkPage ? 'none' : 'flex')};
  width: 100%;
  padding: 27px 0;
  height: 94px;
  margin-bottom: 20px;
`;
export const Title = styled.div`
  ${({ theme }) => theme.fontStyles.logoMedium};
`;
export const AccountImg = styled.img`
  width: 44px;
  height: 44px;
  border-radius: 100%;
`;
