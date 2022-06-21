import styled from 'styled-components';

export type SubmitButtonType = {
  width: string;
  height: string;
};

export const SubmitButtonBox = styled.button<SubmitButtonType>`
  width: ${({ width }) => width};
  height: ${({ height }) => height};
  background-color: #238636;
  color: #fff;
  border-radius: 5px;
  cursor: pointer;
`;
