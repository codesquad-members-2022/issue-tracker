import * as I from 'design/icons';
import * as S from 'components/LabelPage/styled.newLabelList';
import LabelItem from 'components/LabelPage/LabelItem';

interface label {
  title: string;
  color: string;
  description: string;
}

interface Props {
  label: label;
  isNewLabel: boolean;
}

function NewLabelList({ label, isNewLabel }: Props) {
  return (
    <S.newLabelListWrapper>
      <LabelItem label={label} key="key" isLastList={false} />
      <S.labelForm>
        <S.labelName>
          <span>Label name</span>
          <S.labelInput />
        </S.labelName>
        <S.labelDescription>
          <span>Description</span>
          <S.labelInput />
        </S.labelDescription>
        <S.labelColor>
          <span>Color</span>
          <S.labelColorController>
            <S.labelColorButton>
              <I.refresh />
              <S.labelInput />
            </S.labelColorButton>
          </S.labelColorController>
        </S.labelColor>
        <S.buttonsWrapper>
          <S.buttonWrapperTop>
            <S.deleteButton isNewLabel={isNewLabel}>
              <I.trash />
              삭제
            </S.deleteButton>
          </S.buttonWrapperTop>
          <S.buttonWrapperBottom>
            <S.cancleButton>
              <I.cross />
              취소
            </S.cancleButton>
            <S.saveButton>
              <I.edit />
              완료
            </S.saveButton>
          </S.buttonWrapperBottom>
        </S.buttonsWrapper>
      </S.labelForm>
    </S.newLabelListWrapper>
  );
}
export default NewLabelList;
