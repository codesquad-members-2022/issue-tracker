import styled from 'styled-components';

export type TitleBoxType = {
  width: string;
  height: string;
};

export const TitleBox = styled.input.attrs({ type: 'textarea' })<TitleBoxType>`
  width: ${({ width }) => width};
  height: ${({ height }) => height};
  padding: 10px 15px;
  border-radius: 5px;
  &:focus {
    border: 2px solid #57a6ff;
  }
  &:focus-visible {
    outline: none;
  }
`;
