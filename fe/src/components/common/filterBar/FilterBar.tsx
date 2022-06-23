import React, { useState, useRef, useEffect } from "react";
import styled, { css } from "styled-components";

import { filterBarStyle, filterStyle } from "constants/filterBarStyle";
import { DEFAULT_VALUE } from "constants/filterBarText";
import { FlexCenterBox } from "styles/boxes";
import Indicator from "../Indicator";
import Panel from "../panel/Panel";
import FilterBarInput from "./FilterBarInput";
import { FilterBarProps, StyledFilterBarProps } from "./FilterBarInterface";

function FilterBar({ menuList, handlePanelClick }: FilterBarProps) {
  const [isOpen, setIsOpen] = useState(false);
  const [inputValue, setInputValue] = useState(DEFAULT_VALUE);
  const [isFocus, setIsFocus] = useState(false);

  const filterRef: any = useRef(null);
  const inputRef = useRef(null);

  useEffect(() => {
    const handleClickedOutside = ({ target }) => {
      if (isOpen && filterRef && !filterRef.current.contains(target)) {
        setIsOpen(false);
      }
    };

    document.addEventListener("mousedown", handleClickedOutside);

    return () => {
      document.removeEventListener("mouseDown", handleClickedOutside);
    };
  }, [isOpen]);

  useEffect(() => {
    const handleClickInput = () => {
      inputRef.current === document.activeElement ? setIsFocus(true) : setIsFocus(false);
    };

    document.addEventListener("click", handleClickInput);
  }, [isFocus]);

  const handleIndicatorClick = () => (isOpen ? setIsOpen(false) : setIsOpen(true));

  return (
    <div style={{ position: "relative" }}>
      <StyledFilterBar isFocus={isFocus}>
        <Filter as="button" type="button" ref={filterRef} isFocus={isFocus}>
          <Indicator {...filterStyle.indicator} onClick={handleIndicatorClick} />
          <Panel
            {...filterStyle.panel}
            isOpen={isOpen}
            menuList={menuList}
            type="checkbox"
            onClick={handlePanelClick}
          />
        </Filter>
        <FilterBarInput isFocus={isFocus} inputRef={inputRef} inputValue={inputValue} setInputValue={setInputValue} />
      </StyledFilterBar>
    </div>
  );
}

const StyledFilterBar = styled.form<StyledFilterBarProps>`
  ${({ theme: { colors }, isFocus }) => {
    const { size, initial, active } = filterBarStyle.overall;

    return css`
      display: flex;
      width: ${size.width}px;
      height: ${size.height}px;
      border: 1px solid ${isFocus ? colors[active.borderColor] : colors[initial.borderColor]};
      border-radius: 11px;
      overflow: hidden;
    `;
  }}
`;

const Filter = styled(FlexCenterBox)<StyledFilterBarProps>`
  ${({ theme: { colors }, isFocus }) => {
    const { width, initial, hover, active } = filterBarStyle.filter;

    return css`
      width: ${width};
      height: 100%;
      background: ${isFocus ? colors[active.bgColor] : colors[initial.bgColor]};
      border-right: 1px solid ${colors[initial.borderColor]};

      &:hover {
        background: ${colors[hover.bgColor]};
      }
    `;
  }}
`;

export { FilterBar };
