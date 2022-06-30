/* eslint-disable no-console */
import { useAccessToken } from 'hooks/useAccessToken';
import Header from '../../component/Header';
import * as S from './Issues.styled';
import Filter from './Filter';
import MoveBtn from './MoveBtn';
import IssueHeader from './IssueHeader';
import Issue from './Issue';

export function Issues() {
  const accessToken = useAccessToken();

  console.log(accessToken.fetchStatus);

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
      <button
        type="button"
        onClick={() => {
          accessToken.setIsTrigger(prev => !prev);
        }}
      >
        페치요청버튼
      </button>
    </div>
  );
}
