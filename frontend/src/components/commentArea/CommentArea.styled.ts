import styled from 'styled-components';

export type CommentBoxType = {
  width: string;
  height: string;
};

export const CommentBox = styled.textarea<CommentBoxType>`
  width: ${({ width }) => width};
  height: ${({ height }) => height};
  padding: 10px 15px;
  border: 1px solid #383e47;
  border-radius: 5px;
  background-color: #22272e;
  color: #adbac7;
  &:focus {
    border: 2px solid #57a6ff;
  }
  &:focus-visible {
    outline: none;
  }
`;
