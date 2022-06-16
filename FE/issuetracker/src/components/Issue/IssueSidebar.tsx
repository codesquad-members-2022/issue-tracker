import * as I from 'design/icons';
import * as S from 'components/Issue/styled.issueSidebar';
import userImageURL from 'assets/images/UserImageLarge.svg';
import userImageURL2 from 'assets/images/UserImageLarge2.svg';

function IssueSidebar() {
  return (
    <S.sidebar>
      <S.userBar>
        <S.barHeader>
          <S.barHeaderText>작성자</S.barHeaderText>
          <I.plus />
        </S.barHeader>
        <S.userUnit>
          <img src={userImageURL} alt={userImageURL} />
          <S.userName>Oni</S.userName>
        </S.userUnit>
        <S.userUnit>
          <img src={userImageURL2} alt={userImageURL2} />
          <S.userName>Daniel</S.userName>
        </S.userUnit>
      </S.userBar>
      <S.labelBar>
        <S.barHeader>
          <S.barHeaderText>레이블</S.barHeaderText>
          <I.plus />
        </S.barHeader>
      </S.labelBar>
      <S.milestoneBar>
        <S.barHeader>
          <S.barHeaderText>마일스톤</S.barHeaderText>
          <I.plus />
        </S.barHeader>
      </S.milestoneBar>
    </S.sidebar>
  );
}
export default IssueSidebar;
