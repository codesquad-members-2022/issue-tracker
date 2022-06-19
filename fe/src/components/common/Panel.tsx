import React from "react";
import styled, { css } from "styled-components";

import { panelStyle as style } from "constants/dropDownMenuStyle";
import Thumbnail from "components/common/Thumbnail";
import RadioButton from "./button/RadioButton";

interface ThumbnailData {
  url?: string;
  hex?: string;
}

interface MenuItem {
  text: string;
  thumbnail?: ThumbnailData;
}

interface MenuList {
  title: string;
  items: MenuItem[];
}

interface PanelProps {
  width?: number;
  menuList?: MenuList;
  type?: "checkbox" | "list";
}

interface StyledPanelProps {
  width: number;
}

function Panel({ width = 240, menuList = { title: "Title", items: [{ text: "item" }] }, type = "list" }: PanelProps) {
  return (
    <StyledPanel width={width}>
      <MenuTitle>{menuList.title}</MenuTitle>
      {menuList.items.map(({ text, thumbnail }) => (
        <MenuItem key={text}>
          {thumbnail ? <Thumbnail data={thumbnail} /> : null}
          {text}
          {type === "checkbox" ? <RadioButton width={16} height={16} /> : null}
        </MenuItem>
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

const MenuItem = styled.li`
  display: flex;
  align-items: center;
  padding: 8px 16px;

  &:not(:last-child) {
    border-bottom: 1px solid ${({ theme: { colors } }) => colors[style.borderColor]};
  }
`;

const MenuTitle = styled(MenuItem)`
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
