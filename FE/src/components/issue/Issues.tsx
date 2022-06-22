import React, { useEffect } from 'react';
import styles from './Issue.module.scss';
import IssuesNav from './IssuesNav';
import Issue from './Issue';
import { useIssueQuery, fetchedContentType } from '../../hooks/useIssueQuery';
import { useCheckbox } from '../../hooks/useCheckbox';

const Issues = () => {
  const { status, data, dataUpdatedAt } = useIssueQuery('testToken');
  const {
    checkedIssues,
    allCheckboxHandler,
    checkboxHandler,
    allBoxIsChecked,
  } = useCheckbox(data);

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
            />
          ),
        )}
    </div>
  );
};

export default Issues;
