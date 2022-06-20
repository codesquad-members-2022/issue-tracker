import React from "react";
import styled, { css } from "styled-components";

import { progressStyle } from "constants/progressStyle";
import { ProgressProps } from "./progressInterface";
import { FlexCenterBox } from "styles/boxes";

function ProgressInfo({ data }: ProgressProps) {
  const { value, count } = data;
  return (
    <StyledProgressInfo>
      <InfoText>{`${value}%`}</InfoText>
      <FlexCenterBox gap={8}>
        <InfoText>{`열린 이슈 ${count?.open}`}</InfoText>
        <InfoText>{`닫힌 이슈 ${count?.close}`}</InfoText>
      </FlexCenterBox>
    </StyledProgressInfo>
  );
}

const StyledProgressInfo = styled.div`
  display: flex;
  justify-content: space-between;
  margin-top: 8px;
`;

const InfoText = styled.span`
  ${({ theme: { fontSize, fontWeight, colors } }) => {
    const { fontSize: ftSize, fontWeight: ftWeight, fontColor } = progressStyle.info;

    return css`
      color: ${colors[fontColor]};
      font-weight: ${fontWeight[ftWeight]};
      font-size: ${fontSize[ftSize]};
    `;
  }}
`;

export default ProgressInfo;
