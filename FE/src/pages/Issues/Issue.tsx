import { useEffect, useState } from 'react';
import * as S from './Issues.styled';
import { IssueDatasType } from './Constants';

function Issue() {
  const [issueData, setIssueData] = useState([]);
  useEffect(() => {
    fetch('/issues')
      .then(res => res.json())
      .then(data => {
        setIssueData(data);
      });
  }, []);
  return (
    <>
      {issueData.map(
        ({
          id,
          title,
          author,
          timeStamp,
          label,
          mileStone,
        }: IssueDatasType) => (
          <S.Issues key={id}>
            <S.IssuesLeft>
              <S.IssueTitleWrapper>
                <S.Check type="checkbox" />
                <S.IssueTitle>
                  <img src="icons/issueTitleIcon.svg" alt="" />
                  <p>{title}</p>
                </S.IssueTitle>
                <S.LabelTitle>{label}</S.LabelTitle>
              </S.IssueTitleWrapper>
              <S.IssueContentWrapper>
                <S.IssueNumber>#{id}</S.IssueNumber>
                <S.TimeStamp>
                  이 이슈가 {timeStamp}전 {author}님에 의해 작성되었습니다.
                </S.TimeStamp>
                <S.MileStone>
                  <img src="icons/milstoneIcon.svg" alt="" />
                  <p>{mileStone}</p>
                </S.MileStone>
              </S.IssueContentWrapper>
            </S.IssuesLeft>
            <S.IssuesRight>
              <img src="icons/userSmallIcon.svg" alt="" />
            </S.IssuesRight>
          </S.Issues>
        ),
      )}
    </>
  );
}

export default Issue;
