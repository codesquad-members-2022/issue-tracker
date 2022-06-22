import styled from 'styled-components';

const $ProfileWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  overflow: hidden;
  border: 1px solid ${({ theme }) => theme.COLOR.line};
  border-radius: 50%;
  width: 45px;
  height: 45px;
`;

const $ProfileImg = styled.img`
  width: 100%;
  max-width: 100%;
`;

export { $ProfileWrapper, $ProfileImg };
