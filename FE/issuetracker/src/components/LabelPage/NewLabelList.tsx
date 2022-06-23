import * as I from 'design/icons';
import * as S from 'components/LabelPage/styled/styled.newLabelList';
import LabelItem from 'components/LabelPage/LabelItem';
import { useRecoilState, useResetRecoilState } from 'recoil';
import { labelState } from 'context/label';
import React from 'react';

type LabelListType = {
  isNewLabel: boolean;
};

function NewLabelList({ isNewLabel }: LabelListType) {
  const [newLabel, setNewLabel] = useRecoilState(labelState);
  function makeNewLabelInfo(e: React.FocusEvent<HTMLInputElement, Element>) {
    const { value, name } = e.target;
    setNewLabel({
      ...newLabel,
      [name]: value,
    });
  }

  return (
    <S.newLabelListWrapper>
      <LabelItem label={newLabel} key="key" isLastList={false} isNewLabel={isNewLabel} />
      <S.labelForm>
        <S.labelName>
          <span>Label name</span>
          <S.labelInput
            name="title"
            onBlur={(e) => {
              makeNewLabelInfo(e);
            }}
          />
        </S.labelName>
        <S.labelDescription>
          <span>Description</span>
          <S.labelInput
            name="description"
            onBlur={(e) => {
              makeNewLabelInfo(e);
            }}
          />
        </S.labelDescription>
        <S.labelColor>
          <span>Color</span>
          <S.labelColorController>
            <S.labelColorButton>
              <I.refresh />
              <S.labelInput
                name="color"
                onBlur={(e) => {
                  makeNewLabelInfo(e);
                }}
              />
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
            <S.cancleButton onClick={useResetRecoilState(labelState)}>
              <I.cross />
              취소
            </S.cancleButton>
            <S.saveButton
              onClick={() => {
                console.log(newLabel);
              }}
            >
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
