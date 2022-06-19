import React, { useState, useEffect, useRef } from "react";
import styled, { css } from "styled-components";

import Indicator from "./Indicator";
import Panel from "./Panel/Panel";
import { MenuList } from "./Panel/PanelInterface";
import { DropDownDefaultStyle, DropDownStyle } from "../../constants/dropDownMenuStyle";

interface DropDownMenuProps {
  style?: DropDownStyle;
  menuList?: MenuList;
  type?: "checkbox" | "list";
  onClick?: () => void;
}

interface StyledDropDownProps {
  width: number;
  height: number;
}

function DropDownMenu({
  style = DropDownDefaultStyle,
  menuList = { title: "Title", items: [{ text: "item" }] },
  type = "list",
  onClick,
}: DropDownMenuProps) {
  const [isOpen, setIsOpen] = useState(false);
  const dropDownRef: any = useRef(null);

  useEffect(() => {
    const handleClickedOutside = ({ target }) => {
      if (isOpen && dropDownRef && !dropDownRef.current.contains(target)) {
        setIsOpen(false);
      }
    };

    document.addEventListener("mousedown", handleClickedOutside);

    return () => {
      document.removeEventListener("mouseDown", handleClickedOutside);
    };
  }, [isOpen]);

  const handleIndicatorClick = () => (isOpen ? setIsOpen(false) : setIsOpen(true));

  return (
    <StyledDropDown ref={dropDownRef} width={style.indicator.width} height={style.indicator.height}>
      <Indicator {...style.indicator} onClick={handleIndicatorClick} />
      <Panel {...style.panel} isOpen={isOpen} menuList={menuList} type={type} onClick={onClick} />
    </StyledDropDown>
  );
}

const StyledDropDown = styled.div<StyledDropDownProps>`
  ${({ width, height }) => {
    return css`
      position: relative;
      width: ${width}px;
      height: ${height}px;
      right: 0;
    `;
  }}
`;

export default DropDownMenu;
