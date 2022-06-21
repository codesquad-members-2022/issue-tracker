import { flexLayoutMixin } from 'src/utils/utils';
import styled from 'styled-components';

export const HeaderBox = styled.div`
  ${flexLayoutMixin('row', 'center', 'center')};
  width: 63%;
  color: #adbac7;
  fill: #adbac7;
  gap: 16px;
`;

export const FilterBox = styled.div`
  ${flexLayoutMixin('row', 'space-between', 'center')}
  line-height: 23px;
  flex-grow: 1;
`;

export const FilterButton = styled.div`
  ${flexLayoutMixin('row', 'space-between', 'center')}
  width: 90px;
  padding: 5px 16px;
  font-weight: 500;
  background-color: #373e47;
  border-radius: 6px 0 0 6px;
  &:hover {
    background-color: #444c56;
    cursor: pointer;
  }
`;

export const FilterInput = styled.input`
  padding: 5px 16px;
  line-height: 19px;
  background-color: #22272e;
  box-sizing: border-box;
  border: 2px solid #373e47;
  border-radius: 0 6px 6px 0;
  flex-grow: 1;
  color: #768390;
  font-size: 0.875rem;
  &:focus {
    box-sizing: border-box;
    outline: 0;
    border: 2px solid #539bf5;
  }
`;

export const AttributesBox = styled.div`
  ${flexLayoutMixin('row', 'center', 'center')};
  border-radius: 0 6px 6px 0;
`;

export const NewIssueButton = styled.div`
  background-color: #347d39;
  padding: 5px 16px;
  font-size: 0.875rem;
  font-weight: 500;
  color: white;
  line-height: 23px;
  border-radius: 6px;
  white-space: nowrap;
  &:hover {
    background-color: #46954a;
    cursor: pointer;
  }
`;
