import * as I from 'design/icons';
import * as S from 'components/LabelPage/styled.labelItem';
import { LabelType } from 'context/label';
import Label from 'components/common/Common';

type LabelItemType = {
  label: LabelType;
  key: string;
  isLastList: boolean;
};

function LabelItem({ label, key, isLastList }: LabelItemType) {
  return (
    <S.labelList key={key} isLastList={isLastList}>
      <S.labelImageWrapper>
        <Label color={label.color} title={label.title} />
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
