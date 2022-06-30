import React, { Dispatch } from 'react';

import styled from 'styled-components';

import ColorCodeInput from 'components/Atoms/ColorCode';
import Input from 'components/Atoms/Input';
import RadioOption from 'components/Atoms/Radio';
import { LabelTypes } from 'components/Atoms/Label';

import useInput from 'hooks/useInput';

interface TextOptionsTypes {
  id: number;
  label: string;
  handleOnClick: () => void;
}

interface LabelInputTypes {
  mode: 'ADD' | 'CONFIRM';
  labelData: LabelTypes;
  setLabelData: Dispatch<React.SetStateAction<LabelTypes>>;
  setDisableFinishButton: Dispatch<React.SetStateAction<boolean>>;
}

const ColorOptionWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 24px;

  & > form:first-child {
    margin-right: 16px;
  }
`;

const LabelInput = ({ mode, labelData, setLabelData, setDisableFinishButton }: LabelInputTypes) => {
  const {
    isActive: isActiveTitle,
    onChangeInput: onChangeInputTitle,
    onClickInput: onClickInputTitle,
    onBlurInput: onBlurInputTitle,
  } = useInput();
  const {
    isActive: isActiveDesc,
    onChangeInput: onChangeInputDesc,
    onClickInput: onClickInputDesc,
    onBlurInput: onBlurInputDesc,
  } = useInput();

  // title, description Input 관련
  const changeTitleInput = (e: React.FormEvent<HTMLInputElement>) => {
    onChangeInputTitle(e);

    const target = e.target as HTMLInputElement;
    setLabelData({ ...labelData, title: target.value });

    if (mode === 'ADD') {
      return target.value ? setDisableFinishButton(false) : setDisableFinishButton(true);
    }
  };

  const changeDescInput = (e: React.FormEvent<HTMLInputElement>) => {
    onChangeInputDesc(e);
    const target = e.target as HTMLInputElement;
    setLabelData({ ...labelData, description: target.value });
  };

  const inputInfo = [
    {
      id: 0,
      isActive: isActiveTitle,
      inputMaxLength: 12,
      inputPlaceholder: '레이블 이름',
      inputValue: labelData.title,
      onBlur: onBlurInputTitle,
      onChange: changeTitleInput,
      onClick: onClickInputTitle,
    },
    {
      id: 1,
      isActive: isActiveDesc,
      inputMaxLength: 100,
      inputPlaceholder: '설명(선택)',
      inputValue: labelData.description,
      onBlur: onBlurInputDesc,
      onChange: changeDescInput,
      onClick: onClickInputDesc,
    },
  ];

  const Inputs = inputInfo.map((input) => {
    return <Input key={input.id} {...input} inputSize="SMALL" inputType="text" />;
  });

  // colorCode 관련
  const handleOnClickRandomHexCode = () => {
    const randomHexCode = `#${Math.floor(Math.random() * (0xffffff + 1))
      .toString(16)
      .padStart(6, '0')}`;
    setLabelData({ ...labelData, backgroundColor: randomHexCode });
  };

  const handleOnChangeHexCode = (bgColor: string) => setLabelData({ ...labelData, backgroundColor: `#${bgColor}` });

  // textColor 관련
  const textOptions: TextOptionsTypes[] = [
    { id: 1, label: '어두운 색', handleOnClick: () => setLabelData({ ...labelData, titleColor: 'black' }) },
    { id: 2, label: '밝은 색', handleOnClick: () => setLabelData({ ...labelData, titleColor: 'white' }) },
  ];

  return (
    <>
      {Inputs}
      <ColorOptionWrapper>
        <ColorCodeInput
          colorCode={labelData.backgroundColor.replace(/[^a-zA-Z0-9]/gi, '')}
          handleOnClick={handleOnClickRandomHexCode}
          handleOnChange={handleOnChangeHexCode}
        />
        <RadioOption title="텍스트 색상" textOptions={textOptions} />
      </ColorOptionWrapper>
    </>
  );
};

export default LabelInput;
