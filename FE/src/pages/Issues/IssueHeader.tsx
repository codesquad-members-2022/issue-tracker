import * as S from './Issues.styled';
import { ListItem, filterItems } from './Data';

function IssueHeader() {
  return (
    <S.IssueHeader>
      <S.Issue>
        <S.Check type="checkbox" />
        <S.OpenedIssue>
          <img src="icons/openIssueIcon.svg" alt="" />
          <p>열린 이슈(2)</p>
        </S.OpenedIssue>
        <S.ClosedIssue>
          <img src="icons/closeIssueIcon.svg" alt="" />
          <p>닫힌 이슈(3)</p>
        </S.ClosedIssue>
      </S.Issue>
      <S.Table>
        {filterItems.map(({ id, item }: ListItem) => (
          <S.TableList key={id}>
            <p>{item}</p>
            <img src="icons/filterIcon.svg" alt="" />
          </S.TableList>
        ))}
      </S.Table>
    </S.IssueHeader>
  );
}

export default IssueHeader;
