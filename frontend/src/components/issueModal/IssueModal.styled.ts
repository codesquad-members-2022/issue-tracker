import styled from 'styled-components';

export const IssueModalBox = styled.div`
  position: absolute;
  background-color: #161b22;
  border: 1px solid #383e47;
  border-radius: 5px;
  z-index: 1;
  top: 25px;
  right: 0px;
  width: 298px;
  height: 120px;
`;

export const Header = styled.div`
  height: 32px;
  padding: 10px;
  color: #c8d1d9;
  border-bottom: 1px solid #383e47;
`;

export const Main = styled.div`
  padding: 10px;
  color: #6d7681;
  border-bottom: 1px solid #383e47;
`;

export const Bottom = styled.div`
  padding: 15px 10px;
  color: #8b949e;
`;

export const SearchBarBox = styled.input.attrs({ type: 'textarea' })`
  width: 278px;
  height: 28px;
  padding: 10px 15px;
  border: 1px solid #383e47;
  border-radius: 5px;
  background-color: #22272e;
  color: #adbac7;
  &:focus {
    border: 1px solid #57a6ff;
  }
  &:focus-visible {
    outline: none;
  }
`;
