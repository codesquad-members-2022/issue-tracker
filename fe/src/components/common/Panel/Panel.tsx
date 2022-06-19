import React from "react";
import styled, { css } from "styled-components";

import { panelStyle as style } from "constants/dropDownMenuStyle";
import { PanelProps, StyledPanelProps } from "./PanelInterface";
import PanelMenuItem from "./PanelMenuItem";

function Panel({ width, position, mg = `0`, menuList, type = "list", isOpen = false, onClick }: PanelProps) {
  return (
    <StyledPanel width={width} isOpen={isOpen} position={position} mg={mg}>
      <PanelMenuTitle>{menuList.title}</PanelMenuTitle>
      {menuList.items.map(({ text, thumbnail }) => (
        <PanelMenuItem key={text} type={type} text={text} thumbnail={thumbnail} onClick={onClick} />
      ))}
    </StyledPanel>
  );
}

const StyledPanel = styled.ul<StyledPanelProps>`
  ${({ theme: { fontSize, fontWeight, colors }, width, position, mg, isOpen }) => {
    const defaultStyle = style.default;

    return css`
      position: absolute;
      display: ${isOpen ? "block" : "none"};
      ${position === "left" ? `left: 0` : `right: 0`};
      margin: ${mg};
      width: ${width}px;
      background-color: ${colors[defaultStyle.bgColor]};
      color: ${colors[defaultStyle.fontColor]};
      font-weight: ${fontWeight[defaultStyle.fontWeight]};
      font-size: ${fontSize[defaultStyle.fontSize]};
      border: 1px solid ${colors[style.borderColor]};
      border-radius: 16px;
      overflow: hidden;
      line-height: 28px;
    `;
  }}
`;

const PanelMenuTitle = styled.div`
  ${({ theme: { fontSize, colors } }) => {
    const titleStyle = style.title;

    return css`
      padding: 8px 16px;
      background-color: ${colors[titleStyle.bgColor]};
      color: ${colors[titleStyle.fontColor]};
      font-size: ${fontSize[titleStyle.fontSize]};
      line-height: 32px;
    `;
  }}
`;

export default Panel;
