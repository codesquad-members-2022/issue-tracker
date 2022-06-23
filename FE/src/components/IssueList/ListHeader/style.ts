import styled from 'styled-components';

const $ListHeader = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 65px;
  padding: 0 30px;
  background-color: ${({ theme }) => theme.COLOR.background};
  border-bottom: 1px solid ${({ theme }) => theme.COLOR.line};
`;

const $IssueTabWrapper = styled.div`
  display: flex;
  gap: 25px;
  align-items: center;
  justify-content: space-between;
`;

const $FilterMenuWrapper = styled.div`
  display: flex;
  gap: 32px;
  align-items: center;
  justify-content: space-between;
`;

export { $ListHeader, $IssueTabWrapper, $FilterMenuWrapper };
