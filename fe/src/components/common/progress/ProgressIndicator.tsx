import React from "react";
import styled, { css } from "styled-components";

import { progressStyle } from "constants/progressStyle";
import ProgressInfo from "./ProgressInfo";
import { ProgressProps, StyledProgressProps } from "./progressInterface";

function ProgressIndicator({ width = 200, data }: ProgressProps) {
  return (
    <StyledProgress width={width}>
      <ProgressBar max="100" value={data.value} />
      {data.count ? <ProgressInfo data={data} /> : null}
    </StyledProgress>
  );
}

const StyledProgress = styled.div<StyledProgressProps>`
      width: ${({width}) => width}px;
`;

const ProgressBar = styled.progress`
  ${({ theme: { colors } }) => {
    const { bgColor, valueColor } = progressStyle.bar;

    return css`
      top: 0;
      width: 100%;
      height: 8px;
      -webkit-appearance: none;

      &::-webkit-progress-bar {
        background: ${colors[bgColor]};
        border: none;
        border-radius: 10px;
      }

      &::-webkit-progress-value {
        background: ${colors[valueColor]};
        border-radius: 10px;
      }
    `;
  }}
`;

export default ProgressIndicator;
