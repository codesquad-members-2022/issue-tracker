import * as S from './TextArea.styled';

function TextArea({ active }: { active: boolean }) {
  return (
    <S.Form active={active}>
      <S.TextArea
        placeholder="코멘트를 입력하세요"
        fontWeight="normal"
        fontSize="sm"
        lineHeight="short"
        color="placeholder"
        active={active}
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
  );
}

export default TextArea;
