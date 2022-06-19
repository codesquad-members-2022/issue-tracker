import React, { useState } from "react";
import styled from "styled-components";

import { panelStyle as style } from "constants/dropDownMenuStyle";
import Thumbnail from "components/common/Thumbnail";
import RadioButton from "../button/RadioButton";
import { PanelMenuItemProps } from "./PanelInterface";

function PanelMenuItem({ type, text, thumbnail, onClick }: PanelMenuItemProps) {
  const [isChecked, setIsChecked] = useState(false);

  const handleClick = () => {
    if (onClick) {
      onClick();
    }
    isChecked ? setIsChecked(false) : setIsChecked(true);
  };

  return (
    <StyledMenuItem onClick={handleClick}>
      {thumbnail ? <Thumbnail data={thumbnail} /> : null}
      {text}
      {type === "checkbox" ? <RadioButton width={16} height={16} isChecked={isChecked} /> : null}
    </StyledMenuItem>
  );
}

const StyledMenuItem = styled.li`
  display: flex;
  align-items: center;
  padding: 8px 16px;
  cursor: pointer;

  &:not(:last-child) {
    border-bottom: 1px solid ${({ theme: { colors } }) => colors[style.borderColor]};
  }
`;

export default PanelMenuItem;
