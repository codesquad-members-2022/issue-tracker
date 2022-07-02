import axios, { AxiosResponse } from 'axios';
import { useContext, useEffect, useState } from 'react';

import Button from '@/components/Button';
import { IssueStatus } from '@/components/IssueTable/type';
import { CheckedIssueIdProvider } from '@/context/CheckedIssueIdProvider';
import { IssueDataContext } from '@/context/IssueDataProvider';

import IssueList from './IssueList';
import IssueMenuBar from './IssueMenuBar';
import * as S from './style';

const ISSUE_API_URL = 'api/issuegroup/1/issues';
const PAGE_SIZE = 10;

export default function IssueTable() {
  const { issueData, setIssueData } = useContext(IssueDataContext);
  const [issueStatus, setIssueStatus] = useState<IssueStatus>('OPEN');
  const [isLoading, setIsLoading] = useState(true);
  const [pageNum, setPageNum] = useState(0);

  const { openIssueCount, closedIssueCount } = issueData;
  const pageNumStandard =
    issueStatus === 'OPEN' ? openIssueCount : closedIssueCount;
  const issuePageNumbers = Array.from(
    { length: Math.ceil(pageNumStandard / PAGE_SIZE) },
    (_, idx) => idx + 1
  );

  useEffect(() => {
    const token = localStorage.getItem('token');
    const getData = async () => {
      const response: AxiosResponse = await axios.get(
        `${process.env.SERVER_ENDPOINT}/${ISSUE_API_URL}`,
        {
          params: { page: pageNum, pageSize: PAGE_SIZE, status: issueStatus },
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );
      setIssueData(response.data);
      setIsLoading(false);
    };
    getData();
  }, [issueStatus, pageNum]);

  useEffect(() => {
    setPageNum(0);
  }, [issueStatus]);

  return (
    <CheckedIssueIdProvider>
      <S.Container>
        <IssueMenuBar issueData={issueData} setIssueStatus={setIssueStatus} />
        {isLoading ? (
          <S.LoadingSpinner />
        ) : (
          <IssueList issueData={issueData} setPageNum={setPageNum} />
        )}
      </S.Container>
      {isLoading ? (
        <div />
      ) : (
        <S.PageNumContainer>
          {issuePageNumbers.map((num) => (
            <Button
              key={num}
              buttonStyle="sort"
              hasIcon={false}
              contents={`${num}`}
              fontSize="20px"
              onClick={() => {
                setPageNum(num - 1);
              }}
            />
          ))}
        </S.PageNumContainer>
      )}
    </CheckedIssueIdProvider>
  );
}
