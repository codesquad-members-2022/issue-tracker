import React, { useState, useRef, useEffect } from "react";
import styled, { css } from "styled-components";

import { filterBarStyle, filterStyle } from "constants/filterBarStyle";
import { DEFAULT_VALUE } from "constants/filterBarText";
import { FlexCenterBox } from "styles/boxes";
import SearchIcon from "components/Icons/Search";
import Indicator from "./Indicator";
import Panel from "./Panel/Panel";
import { MenuList } from "./Panel/PanelInterface";

interface FilterBarProps {
  menuList: MenuList;
  handlePanelClick?: () => void;
}

interface StyledFilterBarProps {
  isFocus: boolean;
}

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
        <InputBox isFocus={isFocus}>
          <SearchIcon />
          <Input
            ref={inputRef}
            placeholder="Search all issues"
            value={inputValue}
            onChange={({ target }) => setInputValue(target.value)}
          />
        </InputBox>
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

const InputBox = styled.div<StyledFilterBarProps>`
  ${({ theme: { colors }, isFocus }) => {
    const { initial, active } = filterBarStyle.input;
    const { width: filterWidth } = filterBarStyle.filter;
    const {
      size: { width: overallWidth },
    } = filterBarStyle.overall;

    return css`
      display: flex;
      align-items: center;
      padding: 0 26px;
      width: ${overallWidth - filterWidth}px;
      height: 100%;
      background: ${isFocus ? colors[active.bgColor] : colors[initial.bgColor]};
      color: ${colors[initial.fontColor]};

      path {
        stroke: ${colors[initial.fontColor]};
      }
    `;
  }}
`;

const Input = styled.input`
  ${({ theme: { colors }, value }) => {
    const { initial, active } = filterBarStyle.input;
    const ftColor = value === DEFAULT_VALUE ? initial.fontColor : active.fontColor;

    return css`
      margin-left: 11px;
      width: 100%;
      height: 100%;
      color: ${colors[ftColor]};

      &::placeholder {
        color: ${colors[ftColor]};
      }
    `;
  }}
`;

export default FilterBar;
