import { IUserProfile } from '@/components/UserProfile/type';
import styled from 'styled-components';

const $ProfileWrapper = styled.div<IUserProfile>`
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border: 1px solid ${({ theme }) => theme.COLOR.line};
  border-radius: 50%;
  width: ${({ size }) => (size === 'large' ? '45px' : '20px')};
  height: ${({ size }) => (size === 'large' ? '45px' : '20px')};
`;

const $ProfileImg = styled.img`
  width: 100%;
  max-width: 100%;
`;

export { $ProfileWrapper, $ProfileImg };
