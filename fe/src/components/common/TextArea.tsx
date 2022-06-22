import React, { useEffect, useState } from "react";
import styled, { css } from "styled-components";

import GripIcon from "../Icons/Grip";
import PaperClipIcon from "../Icons/PaperClip";
import { STYLE, customStyle, typeStyle } from "../../constants/textAreaStyle";

interface InputProps {
  type: string;
}

interface Styles {
  styles?: any;
  type?: string;
}

function TextArea({ type }: InputProps) {
  // const [styles, setStyles] = useState({});
  const styles = {};
  function makeStyle() {
    let styles = {};
    ({ theme: { fontSize, fontWeight, colors }, type }) => {
      const border = type === "initial" || type === "filled" ? "" : `1px solid ${colors.titleActive}`;
      const bgColor = type === "initial" || type === "filled" ? `${colors.inputBackground}` : `${colors.offWhite}`;
      //  setStyles((prev) => {
      //   return { ...prev, border, bgColor, ...typeStyle[STYLE[type]], ...customStyle[type] };
      // });
      styles = { border, bgColor, ...typeStyle[STYLE[type]], ...customStyle[type] };
    };
    console.log(styles);
    return styles;
  }
  makeStyle();

  return (
    <TextAreaWrapper styles={styles}>
      <InputWrapper>
        <PlaceHoler>코멘트를 입력하세요</PlaceHoler>
        <TextInput />
      </InputWrapper>
      <Count>띄어쓰기 포함 15자</Count>
      <IconWrapper>
        <GripIcon />
      </IconWrapper>
      <Line />
      <AddFile>
        <PaperClipIcon />
        <Description>파일 첨부하기</Description>
      </AddFile>
    </TextAreaWrapper>
  );
}

const Count = styled.div`
  position: absolute;
  left: 59.65%;
  top: 54%;
  bottom: 36%;
  font-size: 12px;
  line-height: 20px;
`;

const AddFile = styled.div`
  width: 103px;
  height: 20px;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
  position: absolute;
  bottom: 8%;
`;

const Description = styled.div`
  font-size: 12px;
`;

const Line = styled.div`
  width: 340px;
  border: 1px dashed #d9dbe9;
  position: absolute;

  left: 0%;
  right: 0%;
  top: 74%;
  bottom: 26%;
`;

const TextAreaWrapper = styled.div<Styles>`
  width: 340px;
  height: 200px;
  background: ${({ styles }) => styles.bgColor};
  border-radius: 16px;
  padding: 16px 24px;
  position: relative;
  margin-top: 100px;
  border: ${({ styles }) => styles.border};
`;

const IconWrapper = styled.div`
  position: absolute;
  left: 92.35%;
  right: 2.94%;
  top: 61%;
  bottom: 31%;
`;

const PlaceHoler = styled.div`
  width: 292px;
  height: 28px;
`;

const InputWrapper = styled(PlaceHoler)`
  width: 292px;
  height: 28px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  gap: 8px;
`;

const TextInput = styled.input`
  width: 290px;
  height: 28px;
  display: none;
`;

export default TextArea;
