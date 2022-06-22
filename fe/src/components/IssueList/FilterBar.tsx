import { useState } from 'react';
import styled from 'styled-components';
import { COLOR } from 'styles/color';
import FONT from 'styles/font';
import SIZE from 'styles/size';
import DropdownBtn from '../common/DropdownBtn';
import Icon from '../common/Icon';

function FilterBar({ placeholder }: { placeholder: string }) {
  const [isFocused, setIsFocused] = useState(false);
  const [inputText, setInputText] = useState('');

  const onChange = ({ target }: { target: HTMLInputElement }) => {
    setInputText(target.value);
  };

  return (
    <Wrapper isFocused={isFocused}>
      <DropdownBtn
        name="필터"
        startLocation="LEFT"
        width={`${SIZE.FILTER_BAR.WIDTH}px`}
        height="100%"
        gap={SIZE.FILTER_BAR.GAP}
        className="filter-dropdown"
      >
        <div>이런이런이런</div>
      </DropdownBtn>

      <div className="input-area flex-center">
        <label htmlFor="filter-bar-input" className="flex-center">
          <Icon icon="search" stroke="inherit" />
          <input
            type="text"
            className="input-text"
            placeholder={placeholder}
            onFocus={() => setIsFocused(true)}
            onBlur={() => setIsFocused(false)}
            onChange={onChange}
            value={inputText}
            id="filter-bar-input"
          />
        </label>
      </div>
    </Wrapper>
  );
}

export default FilterBar;

const Wrapper = styled.div<{ isFocused: boolean }>`
  width: 601px;
  height: 40px;
  display: flex;
  border-radius: 11px;
  border: 1px solid ${({ isFocused }) => (isFocused ? COLOR.BLACK : COLOR.GREY[300])};
  background-color: ${({ isFocused }) => (isFocused ? COLOR.WHITE : COLOR.GREY[300])};

  .filter-dropdown:hover {
    background-color: ${COLOR.GREY[300]};
  }

  .input-area {
    width: 472px;
    height: 100%;
    stroke: ${COLOR.GREY[400]};
    background-color: ${({ isFocused }) => (isFocused ? COLOR.WHITE : COLOR.GREY[200])};
    border-left: 1px solid ${COLOR.GREY[300]};
    stroke: ${({ isFocused }) => (isFocused ? COLOR.GREY[500] : COLOR.GREY[400])};

    .input-text {
      width: 400px;
      height: 28px;
      font-size: ${FONT.SIZE.SMALL};
      margin-left: 10px;
      background-color: transparent;

      &::placeholder {
        color: ${COLOR.GREY[400]};
      }

      &:focus {
        color: ${COLOR.BLACK};
      }
    }
  }
`;
