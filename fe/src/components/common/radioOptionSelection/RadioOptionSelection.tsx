import React, { useState } from "react";
import styled, { css } from "styled-components";

import { radioOptionSelectionStyle as style } from "constants/radioOptionSelectionStyle";
import { FlexBetweenBox } from "styles/boxes";
import RadioOption from "./RadioOption";

interface options {
  value: string;
  text: string;
}

interface RadioOptionSelectionProps {
  label: string;
  options: options[];
}

function RadioOptionSelection({ label, options }: RadioOptionSelectionProps) {
  const [checkedInput, setCheckedInput] = useState(null);

  const isChecked = (value) => value === checkedInput;

  return (
    <StyledSelection>
      <Title>{label}</Title>
      <RadioOption
        value={options[0].value}
        text={options[0].text}
        isChecked={isChecked(options[0].value)}
        setCheckedInput={setCheckedInput}
      />
      <RadioOption
        value={options[1].value}
        text={options[1].text}
        isChecked={isChecked(options[1].value)}
        setCheckedInput={setCheckedInput}
      />
    </StyledSelection>
  );
}

const StyledSelection = styled(FlexBetweenBox)`
  ${({ theme: { fontSize, fontWeight, colors } }) => {
    const {
      size: { width, height },
      font: { fontColor, fontSize: ftSize, fontWeight: ftWeight, lineHeight },
      bgColor,
    } = style.overall;

    return css`
      display: flex;
      align-items: center;
      width: ${width}px;
      height: ${height}px;
      padding: 0 24px;
      background: ${colors[bgColor]};
      border-radius: 11px;
      color: ${colors[fontColor]};
      font-size: ${fontSize[ftSize]};
      font-weight: ${fontWeight[ftWeight]};
      line-height: ${lineHeight}px;
    `;
  }}
`;

const Title = styled.div`
  width: 80px;
`;

export default RadioOptionSelection;
