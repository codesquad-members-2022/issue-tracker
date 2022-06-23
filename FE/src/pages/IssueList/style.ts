import styled from 'styled-components';

const $Contents = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  padding: 0 80px;
`;

const $MenuWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  min-width: 1280px;
  margin-bottom: 25px;
`;

const $ButtonWrapper = styled.div`
  display: flex;
  align-items: center;
  gap: 15px;
`;

export { $Contents, $MenuWrapper, $ButtonWrapper };
