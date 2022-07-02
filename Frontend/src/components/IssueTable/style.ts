import styled from 'styled-components';

export const Container = styled.div`
  width: 1280px;
  border: 1px solid ${({ theme }) => theme.colors.greyScale.line};
  border-radius: 16px;
  overflow: hidden;
`;

export const PageNumContainer = styled.div`
  margin: 10px 0 50px 0;
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center')};
`;

export const LoadingSpinner = styled.div`
  margin: 50px auto;
  ${({ theme }) => theme.mixins.flexBox('row', 'center', 'center')};
  width: 50px;
  height: 50px;
  border: solid 15px;
  border-radius: 50%;
  border-color: #007aff #007aff transparent transparent;
  animation: spinning 0.8s infinite;
  @keyframes spinning {
    from {
      transform: rotate(0);
    }
    to {
      transform: rotate(360deg);
    }
  }
`;
