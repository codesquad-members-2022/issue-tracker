import React from "react";
import styled, { css } from "styled-components";

import { labelStyle, smallLabelStyle } from "constants/labelStyle";
import { LabelBox } from "styles/boxes";

interface SmallLabelProps {
  type?: "label" | "line";
  color?: string;
  textStyle?: "dark" | "light";
  text: string;
}

interface StyledSmallLabelProps {
  type: "label" | "line";
  bgColor: string;
  textStyle: "dark" | "light";
  borderColor?: string;
}

function SmallLabel({ type = "label", color = "primary", textStyle = "light", text }: SmallLabelProps) {
  return (
    <StyledSmallLabel style={labelStyle} type={type} bgColor={color} textStyle={textStyle}>
      {text}
    </StyledSmallLabel>
  );
}

const StyledSmallLabel = styled(LabelBox)<StyledSmallLabelProps>`
  ${({ theme: { colors }, type, bgColor, textStyle }) => {
    const { height } = smallLabelStyle;
    const { fontColor: lineFtColor, borderColor: lineBorderColor } = smallLabelStyle.line;
    const fontColor = textStyle === "dark" ? smallLabelStyle.darkText : smallLabelStyle.lightText;

    return css`
      height: ${height}px;
      color: ${type === "line" ? colors[lineFtColor] : colors[fontColor]};
      background: ${type === "line" ? "transparent" : colors[bgColor]};
      ${type === "line" ? `border: 1px solid ${colors[lineBorderColor]}` : null}
    `;
  }}
`;

export default SmallLabel;
