import axios from 'axios';
import { useEffect } from 'react';
import { useSearchParams } from 'react-router-dom';
import Header from '../../component/Header';
import * as S from './Issues.styled';
import Filter from './Filter';
import MoveBtn from './MoveBtn';
import IssueHeader from './IssueHeader';
import Issue from './Issue';

export function Issues() {
  const [searchParams, setSearchParams] = useSearchParams();

  useEffect(() => {
    (async () => {
      const code = searchParams.get('code');
      const URI = `https://github.com/login/oauth/access_token?client_id=${process.env.REACT_APP_GITHUB_CLIENT_ID}&client_secret=${process.env.REACT_APP_GITHUB_CLIENT_SECRET}&code=${code}`;
      const token = await axios.post(URI);
      console.log(token.data);

      // const githubAPI = await axios.get(
      //   `https://api.github.com/user?Authorization=${token}`,
      // );

      // console.log(githubAPI);
    })();
  }, []);

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

