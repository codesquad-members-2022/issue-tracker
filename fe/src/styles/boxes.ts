import styled from "styled-components";

interface FlexCenterBoxProps {
  gap?: number;
}

export const FlexCenterBox = styled.div<FlexCenterBoxProps>`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: ${({ gap }) => gap}px;
`;

interface IconBoxProps {
  width: number;
  height: number;
}

export const IconBox = styled(FlexCenterBox)<IconBoxProps>`
  width: ${({ width }) => width}px;
  height: ${({ height }) => height}px;

  svg {
    width: ${({ width }) => width}px;
    height: ${({ height }) => height}px;
  }
`;
