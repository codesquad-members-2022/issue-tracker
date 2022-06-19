import React, { useState } from "react";
import styled, { css } from "styled-components";

import CheckOffCircleIcon from "components/Icons/CheckOffCircle";
import { IconBox } from "styles/boxes";
import CheckOnCircleIcon from "components/Icons/CheckOnCircle";

interface RadioButtonProps {
  width?: number;
  height?: number;
  isChecked: boolean;
}

interface StyledRadioButtonProps {
  isChecked?: boolean;
}

function RadioButton({ width = 16, height = 16, isChecked = false }: RadioButtonProps) {
  return (
    <StyledRadioButton as="button" type="button" width={width} height={height} isChecked={isChecked}>
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
