import * as S from './NewIssueForm.styled';

export function NewIssueForm({
  isTitle,
  isActive,
}: {
  isTitle: boolean;
  isActive: boolean;
}) {
  return (
    <>
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
          <S.LabelImg src="./Icons.svg" alt="파일 첨부" />
          파일 첨부하기
          <S.InputFile type="file" />
        </S.Label>
      </S.Form>
    </>
  );
}
