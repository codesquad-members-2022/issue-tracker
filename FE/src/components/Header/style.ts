import styled from 'styled-components';

const $Header = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  min-width: 1280px;
  height: 95px;
  padding: 25px 80px;
  background-color: ${({ theme }) => theme.COLOR.background};
`;

export { $Header };
