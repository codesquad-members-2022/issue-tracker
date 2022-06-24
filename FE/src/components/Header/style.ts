import styled from 'styled-components';

const $Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 15px;
  width: 100%;
  height: 95px;
  background-color: ${({ theme }) => theme.COLOR.background};
`;

export { $Header };
