import styled from 'styled-components';

export const IssueList = styled.section`
  width: 100%;
  min-height: 120px;
  border: 1px solid ${({ theme }) => theme.color.line};
  border-radius: 16px;
  display: flex;
  flex-direction: column;
  background-color: ${({ theme }) => theme.color.offwhite};
  overflow: hidden;
`;

export const IssueListHeader = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 24px 32px;
  border-bottom: 1px solid ${({ theme }) => theme.color.line};
  background-color: ${({ theme }) => theme.color.background};
`;

export const IssueHeaderCheckOpen = styled.div`
  display: flex;
  gap: 24px;
`;

export const IssueHeaderFilters = styled.div`
  display: flex;
  gap: 24px;
`;

export const SortDetail = styled.details`
  display: flex;
  justify-content: space-between;
  align-items: center;

  summary {
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 4px;
    min-width: 80px;
  }
`;

export const IssueItems = styled.ul`
  display: flex;
  padding: 24px 32px;
  flex-direction: column;
`;

export const Issue = styled.li`
  display: flex;
  justify-content: space-between;
`;

export const IssueLeft = styled.div`
  display: flex;
  gap: 32px;
`;

export const IssueRight = styled.div`
  display: flex;
  gap: 24px;
`;

export const IssueInfo = styled.div`
  display: flex;
  flex-direction: column;
  gap: 16px;
`;

export const IssueTitle = styled.div`
  display: flex;
`;

export const IssueDescription = styled.div`
  display: flex;
  gap: 16px;
`;

export const IconField = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 80px;
  padding-right: 16px;
`;
