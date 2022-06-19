import React, { useState } from "react";
import styled, { css } from "styled-components";

import CheckOffCircleIcon from "components/Icons/CheckOffCircle";
import { IconBox } from "styles/boxes";
import CheckOnCircleIcon from "components/Icons/CheckOnCircle";

interface RadioButtonProps {
  width?: number;
  height?: number;
}

interface StyledRadioButtonProps {
  isChecked?: boolean;
}

function RadioButton({ width = 16, height = 16 }: RadioButtonProps) {
  const [isChecked, setChecked] = useState(false);

  const handleButtonClick = () => (isChecked ? setChecked(false) : setChecked(true));

  return (
    <StyledRadioButton
      as="button"
      type="button"
      width={width}
      height={height}
      onClick={handleButtonClick}
      isChecked={isChecked}
    >
      {isChecked ? <CheckOnCircleIcon /> : <CheckOffCircleIcon />}
    </StyledRadioButton>
  );
}

const StyledRadioButton = styled(IconBox)<StyledRadioButtonProps>`
  ${({ theme: { colors }, isChecked }) => {
    return css`
      margin-left: auto;
      svg {
        stroke: ${isChecked ? colors.titleActive : colors.body};
      }
    `;
  }}
`;

export default RadioButton;
