import { IssueListProps } from '@/components/IssueTable/type';
import { IssueType } from '@/context/IssueDataProvider';

import IssueItem from '../IssueItem';
import * as S from './style';

export default function IssueList({ issueData }: IssueListProps) {
  const { issues } = issueData;

  if (!issues || issues.length === 0) {
    return <S.ZeroList>There are not any issues</S.ZeroList>;
  }

  return (
    <>
      {issues.map((issue: IssueType) => (
        <IssueItem key={issue.id} issue={issue} />
      ))}
    </>
  );
}
