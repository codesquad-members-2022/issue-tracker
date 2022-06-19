import React from "react";
import styled, { css } from "styled-components";

import { FlexCenterBox } from "styles/boxes";
import DropDownIcon from "components/Icons/DropDown";
import { indicatorStyle as style } from "constants/dropDownMenuStyle";

interface IndicatorProps {
  width: number;
  height: number;
  gap: number;
  text?: string;
  onClick?: () => void;
}

interface StyledIndicatorProps {
  width: number;
  height: number;
}

function Indicator({ width, height, gap, text = "Text", onClick }: IndicatorProps) {
  return (
    <StyledIndicator width={width} height={height} gap={gap} onClick={onClick}>
      {text}
      <DropDownIcon />
    </StyledIndicator>
  );
}

const StyledIndicator = styled(FlexCenterBox)<StyledIndicatorProps>`
  ${({ theme: { fontSize, fontWeight, colors }, width, height }) => {
    const initialColor = style.color.initial;
    const hoverColor = style.color.hover;

    return css`
      width: ${width}px;
      height: ${height}px;
      color: ${colors[initialColor]};
      font-size: ${fontSize[style.fontSize]};
      font-weight: ${fontWeight[style.fontWeight]};
      cursor: pointer;

      &:hover {
        color: ${colors[hoverColor]};
        path {
          stroke: ${colors[hoverColor]};
        }
      }
    `;
  }}
`;

export default Indicator;
