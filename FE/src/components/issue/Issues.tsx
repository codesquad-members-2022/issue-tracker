import React, { useEffect } from 'react';
import styles from './Issue.module.scss';
import IssuesNav from './IssuesNav';
import Issue from './Issue';
import { useQuery } from 'react-query';
import { useCheckbox } from '../../hooks/useCheckbox';

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
  createdAt: string;
};

const Issues = () => {
  const { status, data, dataUpdatedAt } = useQuery('issues', fetchIssues, {
    refetchOnWindowFocus: false,
    retry: false,
  });

  const {
    checkedIssues,
    allCheckboxHandler,
    checkboxHandler,
    allBoxIsChecked,
    chekced,
  } = useCheckbox(data);

  useEffect(() => {
    console.log(checkedIssues);
  }, [checkedIssues]);
  return (
    <div className={styles.wrapper}>
      <IssuesNav
        allCheckboxHandler={allCheckboxHandler}
        checkedIssues={checkedIssues}
        isChecked={allBoxIsChecked}
      />
      {data &&
        data.content.map(
          ({
            issueNumber,
            title,
            milestoneTitle,
            createdAt,
            memberDto,
          }: fetchedContentType) => (
            <Issue
              key={issueNumber}
              id={issueNumber}
              userId={memberDto.memberId}
              userImg={memberDto.avatarUrl}
              title={title}
              milestoneTitle={milestoneTitle}
              createdAt={new Date(createdAt).getTime()}
              fetchedAt={dataUpdatedAt}
              checkboxHandler={checkboxHandler}
              checkedIssues={checkedIssues}
              chekced={chekced}
            />
          ),
        )}
    </div>
  );
};

export default Issues;
