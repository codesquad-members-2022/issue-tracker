import React, { Dispatch, useEffect, useState } from 'react';
import { useMutation, useQueryClient } from 'react-query';

import { addLabel, replaceLabel } from 'api/label';

import Label, { LabelTypes } from 'components/Atoms/Label';
import Button from 'components/Atoms/Button';
import LabelInput from 'components/Organisms/LabelForm/LabelInput';
import { ButtonWrapper, StyledLabelForm, StyledLabelInfo } from 'components/Organisms/LabelForm/index.styles';

import { COMMON_FORM_BUTTON } from 'helpers/constants/ButtonStyles';

export interface LabelFormTypes {
  mode: 'ADD' | 'CONFIRM';
  labelInfo?: LabelTypes;
  setEditLabel?: Dispatch<React.SetStateAction<boolean>>;
  setIsAddLabel?: Dispatch<React.SetStateAction<boolean>>;
}

const DEFAULT_LABEL_INFO: LabelTypes = {
  id: 0,
  title: '',
  titleColor: 'white',
  backgroundColor: '#007AFF',
  description: '',
};

const LabelForm = ({ mode = 'ADD', labelInfo, setEditLabel, setIsAddLabel }: LabelFormTypes) => {
  const queryClient = useQueryClient();
  const { mutate: addLabelMutate } = useMutation(addLabel, {
    onSuccess: () => {
      queryClient.invalidateQueries('labels');
    },
  });

  const { mutate: replaceLabelMutate } = useMutation(replaceLabel, {
    onSuccess: () => {
      queryClient.invalidateQueries('labels');
    },
  });

  const [labelData, setLabelData] = useState<LabelTypes>(labelInfo || DEFAULT_LABEL_INFO);
  const [disableFinishButton, setDisableFinishButton] = useState<boolean>(true);

  const labelTitle: string = labelData.title === '' ? '레이블 이름' : labelData.title;

  useEffect(() => {}, [labelData]);

  const postNewLabelData = () => {
    addLabelMutate(labelData);
    setIsAddLabel?.(false);
  };

  const fetchLabelData = () => {
    replaceLabelMutate(labelData);
    setEditLabel?.(false);
  };

  useEffect(() => {
    if (mode === 'CONFIRM') {
      return JSON.stringify(labelInfo) === JSON.stringify(labelData)
        ? setDisableFinishButton(true)
        : setDisableFinishButton(false);
    }
  }, [labelData]);

  return (
    <StyledLabelForm>
      <div>
        <Label {...labelData} title={labelTitle} />
      </div>
      <StyledLabelInfo>
        <LabelInput {...{ labelData, setLabelData, setDisableFinishButton, mode }} />
        <ButtonWrapper>
          {mode === 'ADD' ? (
            <Button {...COMMON_FORM_BUTTON.add} disabled={disableFinishButton} HandleOnClick={postNewLabelData} />
          ) : (
            <>
              <Button {...COMMON_FORM_BUTTON.confirm.cancel} HandleOnClick={() => setEditLabel?.(false)} />
              <Button
                {...COMMON_FORM_BUTTON.confirm.add}
                disabled={disableFinishButton}
                HandleOnClick={fetchLabelData}
              />
            </>
          )}
        </ButtonWrapper>
      </StyledLabelInfo>
    </StyledLabelForm>
  );
};

export default LabelForm;
