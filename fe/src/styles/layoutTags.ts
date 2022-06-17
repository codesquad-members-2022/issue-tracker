import styled from "styled-components";

interface LayoutButtonProps {
  gap?: number;
}

export const LayoutButton = styled.button<LayoutButtonProps>`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: ${({ gap }) => gap}px;
`;
