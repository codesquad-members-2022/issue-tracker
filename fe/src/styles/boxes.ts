import styled from "styled-components";

export const FlexCenterBox = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
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
