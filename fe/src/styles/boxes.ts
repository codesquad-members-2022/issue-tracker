import styled, { css } from "styled-components";

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

interface LabelBoxProps {
  style: {
    padding: string;
    fontWeight: string;
    fontSize: string;
    borderRadius: number;
  };
}

export const LabelBox = styled(FlexCenterBox)<LabelBoxProps>`
  ${({ theme: { fontSize, fontWeight }, style }) => {
    const { padding, fontWeight: ftWeight, fontSize: ftSize, borderRadius } = style;

    return css`
      display: inline-flex;
      padding: ${padding};
      font-weight: ${fontWeight[ftWeight]};
      font-size: ${fontSize[ftSize]};
      border-radius: ${borderRadius}px;
    `;
  }}
`;
