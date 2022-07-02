import styled from 'styled-components';

export const Container = styled.div<{
  width: string;
  height: string;
}>`
  position: relative;
  background-color: ${({ theme }) => theme.colors.greyScale.background};
  width: ${({ width }) => width};
  height: ${({ height }) => height};
  cursor: pointer;
`;

export const Content = styled.div<{
  width?: string | undefined;
  isStartFromRight?: boolean;
  isClicked: boolean;
}>`
  display: ${({ isClicked }) => (isClicked ? 'block' : 'none')};
  border-top: 1px solid
    ${({ theme, isClicked }) => isClicked && theme.colors.greyScale.line};
  position: absolute;
  width: ${({ width }) => width};
  box-shadow: 2px 2px 2px 2px
    ${({ theme }) => theme.colors.greyScale.inputBackground};
  right: ${({ isStartFromRight }) => isStartFromRight && '0'};
  background-color: ${({ theme }) => theme.colors.greyScale.offWhite};
`;
