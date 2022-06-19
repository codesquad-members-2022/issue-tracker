import React from "react";
import styled, { css } from "styled-components";

import { panelStyle as style } from "constants/dropDownMenuStyle";
import { PanelProps, StyledPanelProps } from "./PanelInterface";
import PanelMenuItem from "./PanelMenuItem";

function Panel({
  width = 240,
  menuList = { title: "Title", items: [{ text: "item" }] },
  type = "list",
  onClick,
}: PanelProps) {
  return (
    <StyledPanel width={width}>
      <PanelMenuTitle>{menuList.title}</PanelMenuTitle>
      {menuList.items.map(({ text, thumbnail }) => (
        <PanelMenuItem key={text} type={type} text={text} thumbnail={thumbnail} onClick={onClick} />
      ))}
    </StyledPanel>
  );
}

const StyledPanel = styled.ul<StyledPanelProps>`
  ${({ theme: { fontSize, fontWeight, colors }, width }) => {
    const defaultStyle = style.default;

    return css`
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
      background-color: ${colors[titleStyle.bgColor]};
      color: ${colors[titleStyle.fontColor]};
      font-size: ${fontSize[titleStyle.fontSize]};
      line-height: 32px;
    `;
  }}
`;

export default Panel;
