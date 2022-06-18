import { AccountImg } from 'components/common/Common';
import * as S from 'components/newIssue/styled.index';
import * as I from 'design/icons';
import AccountSrc from 'assets/images/UserImageLarge.svg';
import { keyMaker } from 'utils/util';

function NewIssue() {
  const additionalContentTitles: Array<string> = ['담당자', '레이블', '마일스톤'];
  const additionalContents = additionalContentTitles.map((content, idx) => {
    const key: string = keyMaker();
    return (
      <S.AdditionalContent key={key} idx={idx}>
        {content} <I.plus />
      </S.AdditionalContent>
    );
  });
  return (
    <S.NewIssueWrap>
      <S.NewIssueTitle>새로운 이슈 작성</S.NewIssueTitle>
<<<<<<< HEAD
      <S.TitleUnderLine />
=======
      <S.UnderLine />
>>>>>>> origin/11-feat-fe-newissue-레이아웃-구현
      <S.NewIssueContent>
        <S.IssueContentLeft>
          <S.ImgAndTitle>
            <AccountImg src={AccountSrc} />
            <S.TitleInput placeholder="제목" />
          </S.ImgAndTitle>
          <S.InputArea>
            <S.NewIssueInput placeholder="코멘트를 입력하세요" />
            <S.AttatchFile>
              <I.paperclip />
              파일 첨부하기
            </S.AttatchFile>
          </S.InputArea>
        </S.IssueContentLeft>
        <S.AdditionalContents>{additionalContents}</S.AdditionalContents>
      </S.NewIssueContent>
<<<<<<< HEAD
=======
      <S.UnderLine />
      <S.Buttons>
        <I.cross />
        <S.CancelText>작성 취소</S.CancelText>
        <S.CompleteBtn>완료</S.CompleteBtn>
      </S.Buttons>
>>>>>>> origin/11-feat-fe-newissue-레이아웃-구현
    </S.NewIssueWrap>
  );
}

export default NewIssue;
