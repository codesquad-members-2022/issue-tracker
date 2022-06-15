import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const IssueListWrap = styled.div``;

export const IssueListTop = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  width: 1280px;
  height: 40px;
`;
export const FilterBar = styled.div`
  ${mixin.flexbox({ horizontal: 'flex-start', vertical: 'center' })};
  width: 600px;
  height: 100%;
`;
export const Filter = styled.button`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
  gap: 12px;
  width: 128px;
  height: 100%;
  border-radius: 11px 0px 0px 11px;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
`;
export const SearchBar = styled.input`
  padding: 6px 24px;
  width: 472px;
  height: 100%;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-radius: 0px 11px 11px 0px;
`;
export const IssueListTopRight = styled.div``;
export const Buttons = styled.div``;

export const LabelButton = styled.button`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
`;
