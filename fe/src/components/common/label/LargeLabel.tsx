import React from "react";
import styled, { css } from "styled-components";

import { labelStyle, largeLabelStyle } from "constants/labelStyle";
import { CLOSE, OPEN } from "constants/labelText";
import { IconBox, LabelBox } from "styles/boxes";
import AlertCircle from "../../Icons/AlertCircle";
import Archive from "../../Icons/Archive";

interface LargeLabelProps {
  type?: "open" | "close";
}

interface StyledLargeLabelProps {
  type: "open" | "close";
}

function LargeLabel({ type = "open" }: LargeLabelProps) {
  const { width: iconWidth, height: iconHeight } = largeLabelStyle.iconSize;

  return (
    <StyledLargeLabel gap={largeLabelStyle.gap} style={labelStyle} type={type}>
      {type === "open" ? (
        <>
          <IconBox width={iconWidth} height={iconHeight}>
            <AlertCircle />
          </IconBox>
          {OPEN}
        </>
      ) : (
        <>
          <IconBox width={iconWidth} height={iconHeight}>
            <Archive />
          </IconBox>
          {CLOSE}
        </>
      )}
    </StyledLargeLabel>
  );
}

const StyledLargeLabel = styled(LabelBox)<StyledLargeLabelProps>`
  ${({ theme: { colors }, type }) => {
    const { height } = largeLabelStyle;
    const { bgColor, fontColor, borderColor } = largeLabelStyle[type];

    return css`
      height: ${height}px;
      color: ${colors[fontColor]};
      background: ${colors[bgColor]};
      border: 1px solid ${colors[borderColor]};

      path {
        stroke: ${colors[fontColor]};
      }
    `;
  }}
`;

export default LargeLabel;
