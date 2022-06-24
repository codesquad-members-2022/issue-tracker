import { NEW_ISSUE_BTN_TEXTS } from 'constants/constants';
import * as S from './NewIssueForm.styled';

export function NewIssueForm({
  isTitle,
  isActive,
}: {
  isTitle: boolean;
  isActive: boolean;
}) {
  const btns = NEW_ISSUE_BTN_TEXTS.map(text => (
    <S.SNBItem key={text.id} sequence={text.id}>
      <S.SNBBtn
        type="button"
        fontWeight="bold"
        fontSize="sm"
        lineHeight="short"
        color="label"
      >
        {text.text}
        <S.SNBBtnImg src="./icons/snbButton.svg" alt={`${text.text} 추가`} />
      </S.SNBBtn>
    </S.SNBItem>
  ));

  return (
    <S.Container>
      <S.User>
        <S.UserImg src="./imgs/UserImageLarge.svg" alt="유저 이미지" />
      </S.User>
      <S.FormContainer>
        <S.Title
          type="text"
          placeholder="제목"
          fontWeight="normal"
          fontSize="sm"
          lineHeight="short"
          color="titleActive"
          isActive={isActive}
          isTitle={isTitle}
        />
        <S.Form isActive={isActive}>
          <S.TextArea
            placeholder="코멘트를 입력하세요"
            fontWeight="normal"
            fontSize="sm"
            lineHeight="short"
            color="titleActive"
            isActive={isActive}
          />
          <S.Label
            fontWeight="bold"
            fontSize="xs"
            lineHeight="shorter"
            color="label"
          >
            <S.LabelImg src="./icons/fileAdd.svg" alt="파일 첨부" />
            파일 첨부하기
            <S.InputFile type="file" />
          </S.Label>
        </S.Form>
      </S.FormContainer>
      <S.SNB>{btns}</S.SNB>
    </S.Container>
  );
}
