import React from "react";
import styled, { css } from "styled-components";

import { IconBox } from "styles/boxes";
import { LayoutButton } from "styles/layoutTags";
import { textButtonStyle as style } from "../../../constants/buttonStyle";

interface TextButtonProps {
  size?: "medium" | "small";
  icon?: any;
  text?: string;
  disabled?: boolean;
}

interface StyledTextButtonProps {
  size: "large" | "medium" | "small";
  gap?: number;
  disabled?: boolean;
}

function TextButton({ size = "small", icon, text, disabled = false }: TextButtonProps) {
  return (
    <StyledTextButton type="button" size={size} gap={4} disabled={disabled}>
      <IconBox width={16} height={16}>
        {icon}
      </IconBox>
      {text}
    </StyledTextButton>
  );
}

const StyledTextButton = styled(LayoutButton)<StyledTextButtonProps>`
  ${({ theme: { fontSize, fontWeight, colors }, size }) => {
    const { width, height, ftSize } = style[size];
    const { initial, active, hover } = style.fontColor;

    return css`
      width: ${width}px;
      height: ${height}px;
      color: ${colors[initial]};
      font-size: ${fontSize[ftSize]};
      font-weight: ${fontWeight.bold};
      path {
        stroke: ${colors[initial]};
      }

      &:hover:not([disabled]) {
        color: ${colors[hover]};
        path {
          stroke: ${colors[hover]};
        }
      }

      &:active:not([disabled]) {
        color: ${colors[active]};
        path {
          stroke: ${colors[active]};
        }
      }

      &[disabled] {
        opacity: 0.5;
      }
    `;
  }}
`;

export default TextButton;
