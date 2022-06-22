import React from 'react';
import { Input } from '@UI/Input';
import styles from './Issue.module.scss';
import IssuesNav from './IssuesNav';
import Issue from './Issue';
import { QueryClient, useQuery } from 'react-query';

const fetchIssues = async () => {
  const response = await fetch(`http://localhost:3030/issues`);
  if (!response.ok) {
    throw new Error('response not ok');
  }

  return response.json();
};

type fetchedDataType = {
  content: fetchedContentType[];
  // 여기에 추가 되는 타입들이 있을 것 같아서, 일단 이렇게 작성했습니다.
};

type fetchedContentType = {
  memberDto: {
    memberId: string;
    avatarUrl: string;
  };
  issueNumber: number;
  title: string;
  milestoneTitle: string;
  createAt: string;
};

const Issues = () => {
  const { status, data, dataUpdatedAt } = useQuery('issues', fetchIssues, {
    refetchOnWindowFocus: false,
    retry: false,
  });

  return (
    <>
      <div className={styles.wrapper}>
        <IssuesNav />
        {data &&
          data.content.map(
            ({ issueNumber, title, milestoneTitle, createdAt, memberDto }) => (
              <Issue
                key={issueNumber}
                id={issueNumber}
                userId={memberDto.memberId}
                userImg={memberDto.avatarUrl}
                title={title}
                milestoneTitle={milestoneTitle}
                createdAt={new Date(createdAt).getTime()}
                fetchedAt={dataUpdatedAt}
              />
            ),
          )}
      </div>
    </>
  );
};

export default Issues;
