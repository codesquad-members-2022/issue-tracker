import * as I from 'design/icons';
import * as S from 'components/LabelPage/styled.labelItem';

interface label {
  title: string;
  color: string;
  description: string;
}

interface Props {
  label: label;
  key: string;
  isLastList: boolean;
}

const initialLabel = {
  title: '레이블 이름',
  color: '',
  description: '레이블에 대한 설명',
};

function LabelItem({ label = initialLabel, key = 'key', isLastList = false }: Props) {
  return (
    <S.labelList key={key} isLastList={isLastList}>
      <S.labelImageWrapper>
        <S.labelImage color={label.color}>{label.title}</S.labelImage>
      </S.labelImageWrapper>
      <S.labelListDescription>{label.description}</S.labelListDescription>
      <S.labelListButtonWrapper>
        <S.labelEditButton>
          <I.edit />
          <S.labelButtonText>편집</S.labelButtonText>
        </S.labelEditButton>
        <S.labelDeleteButton>
          <I.edit />
          <S.labelButtonText>삭제</S.labelButtonText>
        </S.labelDeleteButton>
      </S.labelListButtonWrapper>
    </S.labelList>
  );
}
export default LabelItem;
