import React from "react";
import styled, { css } from "styled-components";

interface ThumbnailProps {
  diameter?: number;
  data: any;
}

interface StyledThumbnailProps {
  width: number;
  height: number;
  bgData: string;
}

function Thumbnail({ diameter = 20, data }: ThumbnailProps) {
  const { hex, url } = data;
  
  return <StyledThumbnail width={diameter} height={diameter} bgData={hex || url} />;
}

const StyledThumbnail = styled.div<StyledThumbnailProps>`
  ${({ width, height, bgData }) => {
    return css`
      width: ${width}px;
      height: ${height}px;
      margin-right: 8px;
      background: ${bgData[0] === "#" ? bgData : `url(${bgData})`};
      background-size: ${width}px ${height}px;
      border-radius: 50%;
    `;
  }}
`;

export default Thumbnail;
