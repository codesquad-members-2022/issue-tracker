import { flexLayoutMixin } from 'src/utils/utils';
import styled from 'styled-components';

const HeaderBox = styled.div`
  ${flexLayoutMixin('row', 'space-between', 'center')}
  padding: 16px 32px;
  background-color: #2d333b;
  color: #cdd9e5;
  fill: #cdd9e5;
  svg:hover {
    cursor: pointer;
    fill: #9eb3b3;
  }
`;

const FlexBox = styled.div`
  ${flexLayoutMixin('row', 'space-between', 'center')}
  gap: 16px;
`;

const SearchBox = styled.div`
  input {
    font-size: 0.875rem;
    min-height: 28px;
    padding: 3px 12px;
    background-color: #22272e;
    border: none;
    color: #c1c3c4;
  }
  input::placeholder {
    color: #c1c3c4;
  }
`;

const NavBar = styled.ul`
  ${flexLayoutMixin('row', 'space-between', 'center')}
  font-weight: 600;
  gap: 16px;

  li:hover {
    cursor: pointer;
    color: #9eb3b3;
  }
`;

const Thumbnail = styled.div``;

export { HeaderBox, SearchBox, NavBar, FlexBox, Thumbnail };
