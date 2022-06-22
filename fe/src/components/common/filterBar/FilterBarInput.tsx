import React from "react";
import styled, { css } from "styled-components";

import { filterBarStyle } from "constants/filterBarStyle";
import { DEFAULT_VALUE, PLACEHOLDER } from "constants/filterBarText";
import SearchIcon from "components/Icons/Search";
import { StyledFilterBarProps } from "./FilterBarInterface";

function FilterBarInput({ isFocus, inputRef, inputValue, setInputValue }) {
  return (
    <InputBox isFocus={isFocus}>
      <SearchIcon />
      <Input
        ref={inputRef}
        placeholder={PLACEHOLDER}
        value={inputValue}
        onChange={({ target }) => setInputValue(target.value)}
      />
    </InputBox>
  );
}

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

export default FilterBarInput;
