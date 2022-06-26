import { IssuePageTitle } from 'style/IssuePageTitle';
import { NewIssueForm } from 'style/NewIssueForm/NewIssueForm';
import * as S from './NewIssue.styled';

export function NewIssue() {
  return (
    <>
      <IssuePageTitle>새로운 이슈 작성</IssuePageTitle>
      <NewIssueForm isTitle isActive={false} />
      <S.Footer>
        <S.Btn
          type="button"
          size="md"
          fontWeight="bold"
          fontSize="md"
          lineHeight="base"
          color="offWhite"
        >
          취소
        </S.Btn>
        <S.Btn
          type="button"
          size="md"
          fontWeight="bold"
          fontSize="md"
          lineHeight="base"
          color="offWhite"
        >
          확인
        </S.Btn>
      </S.Footer>
    </>
  );
}
