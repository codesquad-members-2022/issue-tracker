import styled from 'styled-components';

const $ListHeader = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 65px;
  padding: 0 30px;
  background-color: ${({ theme }) => theme.COLOR.background};
  border-bottom: 1px solid ${({ theme }) => theme.COLOR.line};
  border-top-left-radius: 16px;
  border-top-right-radius: 16px;
`;

const $IssueWrapper = styled.div`
  border-radius: 16px;
  border: 1px solid ${({ theme }) => theme.COLOR.line};
  background-color: ${({ theme }) => theme.PALETTE.WHITE};
`;

const $IssueList = styled.ul`
  li:not(:last-child) {
    border-bottom: 1px solid ${({ theme }) => theme.COLOR.line};
  }
`;

export { $ListHeader, $IssueWrapper, $IssueList };
