import React from "react";
import styled from "styled-components";

import { sizeStyle, STYLE, specificStyle, commonStyle } from "../../constants/textInputStyle";

interface InputProps {
  size?: string;
  type?: string;
}

function Input({ size = "small", type = "typing" }) {
  const style = { ...sizeStyle[STYLE[type]][size], ...commonStyle[STYLE[type]], ...specificStyle[type] };
  console.log(style);
  return (
    <InputWrapper customStyle={style} size={size}>
      <Title customStyle={style}>아이디</Title>
      <TextInput customStyle={style} />
    </InputWrapper>
  );
}

interface StylesProps {
  size?: string;
  customStyle: {
    wrapperSize: { width: number; height: number };
    titleSize: { width: number; height: number };
    backgroundColor: string;
    border: string;
    opacity?: number;
    textInputSize?: { width: number; height: number };
    textInputDisplay: string;
    titleFont: {
      size: string;
      weight: string;
      height: number;
      color: string;
    };
    textInputFont: {
      size: string;
      weight: number;
      height: number;
      color: string;
    };
  };
}

const InputWrapper = styled.div<StylesProps>`
  width: ${({ customStyle }) => customStyle.wrapperSize.width}px;
  height: ${({ customStyle }) => customStyle.wrapperSize.height}px;
  background: ${({ customStyle }) => customStyle.backgroundColor};
  border: ${({ customStyle }) => customStyle.border};
  border-radius: 16px;
  display: flex;
  flex-direction: ${({ size }) => (size === "small" ? "row" : "column")};
  justify-content: center;
  align-items: flex-start;
  padding: 0px 24px;
  margin-top: 100px;
  opacity: ${({ customStyle }) => customStyle.opacity};
`;

const Title = styled.div<StylesProps>`
  width: ${({ customStyle }) => customStyle.titleSize.width}px;
  height: ${({ customStyle }) => customStyle.titleSize.height}px;
  font-size: ${({ customStyle }) => customStyle.titleFont.size};
  font-weight: ${({ customStyle }) => customStyle.titleFont.weight};
  line-height: ${({ customStyle }) => customStyle.titleFont.height}px;
  color: ${({ customStyle }) => customStyle.titleFont.color};
  display: flex;
  align-items: center;
`;

const TextInput = styled.input<StylesProps>`
  width: ${({ customStyle }) => customStyle.textInputSize?.width}px;
  height: ${({ customStyle }) => customStyle.textInputSize?.height}px;
  display: ${({ customStyle }) => customStyle.textInputDisplay};
  align-items: center;
  font-size: ${({ customStyle }) => customStyle.textInputFont?.size};
  font-weight: ${({ customStyle }) => customStyle.textInputFont?.weight};
  line-height: ${({ customStyle }) => customStyle.textInputFont?.height}px;
  color: ${({ customStyle }) => customStyle.textInputFont?.color};
`;

export default Input;
