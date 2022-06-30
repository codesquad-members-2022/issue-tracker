/* eslint-disable jsx-a11y/label-has-associated-control */
import React, { useEffect, useState } from 'react';

import { colors } from 'styles/theme';
import StyledColorCodeInput from 'components/Atoms/ColorCode/index.styles';
import Icon from 'components/Atoms/Icon';

import useInput from 'hooks/useInput';

export interface ColorCodeInputTypes {
  colorCode: string;
  handleOnClick: () => void;
  handleOnChange: (string: string) => void;
}

const ID = 'color_code';
const MAX_LENGTH = 6;

const ColorCodeInput = ({ colorCode, handleOnClick, handleOnChange }: ColorCodeInputTypes) => {
  const { isActive, onClickInput, onBlurInput } = useInput();
  const [hexCode, setHexCode] = useState<string>(colorCode);
  const [isError, setIsError] = useState<boolean>(false);

  const handleOnChangeInput = (e: React.FormEvent<HTMLInputElement>) => {
    const target = e.target as HTMLInputElement;
    const replace = target.value.replace(/[^a-zA-Z0-9]/gi, '');
    setHexCode(replace);

    if (target.value.length !== 6) {
      setIsError(true);
      return;
    }

    handleOnChange(replace);
    setIsError(false);
  };

  const changeLabelColor = () => {
    handleOnClick();
    setHexCode(colorCode);
  };

  useEffect(() => {
    setHexCode(colorCode);
  }, [colorCode]);

  return (
    <StyledColorCodeInput isActive={isActive} isError={isError}>
      <label htmlFor={ID}>Label</label>
      <span>#</span>
      <input
        type="text"
        id={ID}
        name={ID}
        value={hexCode}
        maxLength={MAX_LENGTH}
        onChange={handleOnChangeInput}
        onClick={onClickInput}
        onBlur={onBlurInput}
      />
      <button type="button" onClick={changeLabelColor}>
        <Icon icon="RefreshCcw" stroke={colors.label} />
      </button>
    </StyledColorCodeInput>
  );
};

export default ColorCodeInput;
