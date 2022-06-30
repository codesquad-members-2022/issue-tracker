import styled from 'styled-components';
import { theme } from '@/styles/theme';

export const Callback = styled.div`
  border: 8px solid ${theme.color.titleArchieve}; /* Light grey */
  border-top: 8px solid ${theme.color.offwhite}; /* Blue */
  border-radius: 50%;
  width: 60px;
  height: 60px;
  animation: spinner 0.5s linear infinite;
  @keyframes spinner {
    from {
      transform: rotate(0deg);
    }
    to {
      transform: rotate(360deg);
    }
  }
`;
