import React from "react";
import styled, { css } from "styled-components";

import { radioOptionStyle as style } from "constants/radioOptionSelectionStyle";
import { IconBox } from "styles/boxes";
import CheckOnCircleIcon from "components/Icons/CheckOnCircle";
import CheckOffCircleIcon from "components/Icons/CheckOffCircle";

interface RadioOptionProps {
  value: string;
  text: string;
  isChecked: boolean;
  setCheckedInput: (e: any) => void;
}

function RadioOption({ value, text, isChecked, setCheckedInput }: RadioOptionProps) {
  return (
    <StyledOption>
      <input
        type="radio"
        id={`option-${value}`}
        name="option"
        value={value}
        onChange={({ target }) => setCheckedInput(target.value)}
      />
      <Label htmlFor={`option-${value}`}>
        <IconBox width={16} height={16}>
          {isChecked ? <CheckOnCircleIcon /> : <CheckOffCircleIcon />}
        </IconBox>
        {text}
      </Label>
    </StyledOption>
  );
}

const StyledOption = styled.div`
  ${({ theme: { colors } }) => {
    const {
      size: { width },
      color: { initial, checked },
    } = style;

    return css`
      width: ${width}px;

      input[type="radio"] {
        display: none;
      }

      input[type="radio"] + label {
        color: ${colors[initial]};

        path {
          stroke: ${colors[initial]};
        }
      }

      input[type="radio"]:checked + label {
        color: ${colors[checked]};

        path {
          stroke: ${colors[checked]};
        }
      }
    `;
  }}
`;

const Label = styled.label`
  display: flex;
  align-items: center;
  gap: 8px;
`;

export default RadioOption;
