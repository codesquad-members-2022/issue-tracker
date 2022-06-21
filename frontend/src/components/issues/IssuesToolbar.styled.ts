import { flexLayoutMixin } from 'src/utils/utils';
import styled from 'styled-components';

export const ToolbarBox = styled.div`
  ${flexLayoutMixin('row', 'space-between', 'center')}
  width: 100%;
  background-color: #2d333b;
  padding: 16px;
  color-scheme: dark;
`;

export const ToggleBox = styled.div`
  ${flexLayoutMixin('row', 'center', 'center')}
  gap: 13px;
`;

export const Toggle = styled.div`
  ${flexLayoutMixin('row', 'center', 'center')}
  gap: 7px;
  color: #768390;
  fill: #768390;
  &:hover {
    color: #adbac7;
    fill: #adbac7;
    cursor: pointer;
  }
`;

export const OpenText = styled.div`
  height: 16px;
  line-height: 19px;
`;

export const ClosedText = styled.div`
  height: 16px;
  line-height: 19px;
`;

export const DropdownList = styled.div`
  ${flexLayoutMixin('row', 'center', 'center')}
  color: #768390;
  fill: #768390;
  font-size: 0.875rem;
  div:hover {
    color: #adbac7;
    fill: #adbac7;
    cursor: pointer;
  }
`;
