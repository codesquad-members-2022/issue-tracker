import styled from 'styled-components';

const $IssueWrapper = styled.div`
  width: 100%;
  overflow: hidden;
  border-radius: 16px;
  border: 1px solid ${({ theme }) => theme.COLOR.line};
  background-color: ${({ theme }) => theme.PALETTE.WHITE};
`;

const $IssueList = styled.ul`
  li:not(:last-child) {
    border-bottom: 1px solid ${({ theme }) => theme.COLOR.line};
  }
`;

export { $IssueWrapper, $IssueList };
