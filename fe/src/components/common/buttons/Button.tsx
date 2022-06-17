import styled, { css } from "styled-components";
import { IconBox } from "styles/boxes";
import { LayoutButton } from "styles/layoutTags";
import { buttonStyle as style } from "./buttonStyles";

interface ButtonProps {
  size?: "large" | "medium" | "small";
  set?: "standard" | "secondary";
  icon?: any;
  text?: string;
  disabled?: boolean;
}

interface StyledButtonProps {
  size?: "large" | "medium" | "small";
  set?: "standard" | "secondary";
  gap?: number;
  disabled?: boolean;
}

function Button({ size = "medium", set = "standard", icon, text, disabled = false }: ButtonProps) {
  return (
    <StyledButton type="button" size={size} set={set} gap={4} disabled={disabled}>
      {icon ? (
        <IconBox width={16} height={16}>
          {icon}
        </IconBox>
      ) : null}
      {text}
    </StyledButton>
  );
}

const StyledButton = styled(LayoutButton)<StyledButtonProps>`
  ${({ theme: { fontSize, fontWeight, colors }, size, set }) => {
    const { initial, hover } = style[set];
    const btnSize = style.size[size];
    const btnBorderRadius = style.borderRadius[size];
    const btnFontSize = style.fontSize[size];

    return css`
      width: ${btnSize.width}px;
      height: ${btnSize.height}px;
      background: ${colors[initial.bgColor]};
      color: ${colors[initial.fontColor]};
      font-size: ${fontSize[btnFontSize]};
      font-weight: ${fontWeight.bold};
      border-radius: ${btnBorderRadius}px;
      border: ${set === "secondary" ? `2px solid ${colors[initial.borderColor]}` : "none"};
      path {
        stroke: ${colors[initial.fontColor]};
      }

      &:hover:not([disabled]) {
        background: ${colors[hover.bgColor]};
        color: ${colors[hover.fontColor]};
        border: ${set === "secondary" ? `2px solid ${colors[hover.borderColor]}` : "none"};
        path {
          stroke: ${colors[hover.fontColor]};
        }
      }

      &:focus {
        border: 4px solid ${colors["primaryLight"]};
      }

      &[disabled] {
        opacity: 0.5;
      }
    `;
  }}
`;

export default Button;
