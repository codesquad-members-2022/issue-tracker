import { mixin } from 'design/GlobalStyles';
import styled from 'styled-components';

export const LoginWrap = styled.div`
  ${mixin.flexbox({ dir: 'column', horizontal: 'center', vertical: 'center' })}
  width: 100%;
  height: 100vh;
  background: ${({ theme }) => theme.backgroundColors.gray2};
  gap: 80px;
`;
export const Title = styled.div`
  ${({ theme }) => theme.fontStyles.logoLarge};
  color: ${({ theme }) => theme.fontColors.gray5};
`;
export const GitHubLogin = styled.button`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })}
  width: 340px;
  height: 64px;
  border-radius: 20px;
  background: ${({ theme }) => theme.backgroundColors.gray5};
  ${({ theme }) => theme.fontStyles.linkMedium};
  color: ${({ theme }) => theme.fontColors.gray1};
`;
