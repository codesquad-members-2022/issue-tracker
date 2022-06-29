import Header from '../../component/Header';
import * as S from './Issues.styled';
import Filter from './Filter';
import MoveBtn from './MoveBtn';
import IssueHeader from './IssueHeader';
import Issue from './Issue';

export function Issues() {
  return (
    <div>
      <Header />
      <S.Container>
        <S.Wrapper>
          <S.Header>
            <Filter />
            <MoveBtn />
          </S.Header>
          <S.Content>
            <IssueHeader />
            <Issue />
          </S.Content>
        </S.Wrapper>
      </S.Container>
    </div>
  );
}
