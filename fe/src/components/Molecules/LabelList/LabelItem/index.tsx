import { useState } from 'react';

import Button from 'components/Atoms/Button';
import Label, { LabelTypes } from 'components/Atoms/Label';

import CommonForm from 'components/Molecules/CommonForm';
import LabelForm from 'components/Organisms/LabelForm';
import CommonItem from 'components/Molecules/CommonList/CommonItem';
import { ItemStyle, LabelButtonWrapper, LabelDesc } from 'components/Molecules/LabelList/LabelItem/index.styles';

import { LABEL_ITEM_BUTTON } from 'helpers/constants/ButtonStyles';
import { useMutation, useQueryClient } from 'react-query';
import { deleteLabel } from 'api/label';

const LabelItem = ({ label }: { label: LabelTypes }) => {
  const [editLabel, setEditLabel] = useState<boolean>(false);
  const { id, description } = label;

  const queryClient = useQueryClient();
  const { mutate: deleteLabelMutate } = useMutation(deleteLabel, {
    onSuccess: () => {
      queryClient.invalidateQueries('labels');
    },
  });

  const deleteLabelData = () => {
    if (!id) return;

    deleteLabelMutate(id);
    setEditLabel?.(false);
  };

  const clickEditButton = () => setEditLabel((focus) => !focus);

  return !editLabel ? (
    <CommonItem key={id} style={ItemStyle}>
      <Label {...label} labelStyle="STANDARD" />
      <LabelDesc>{description}</LabelDesc>
      <LabelButtonWrapper>
        <Button {...LABEL_ITEM_BUTTON.edit} HandleOnClick={clickEditButton} />
        <Button {...LABEL_ITEM_BUTTON.delete} HandleOnClick={deleteLabelData} />
      </LabelButtonWrapper>
    </CommonItem>
  ) : (
    <li>
      <CommonForm title="레이블 편집" style={{ border: 'none' }}>
        <LabelForm mode="CONFIRM" labelInfo={label} setEditLabel={setEditLabel} />
      </CommonForm>
    </li>
  );
};

export default LabelItem;
