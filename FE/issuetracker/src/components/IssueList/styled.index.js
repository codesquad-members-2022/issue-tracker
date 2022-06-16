import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const IssueListWrap = styled.div`
  ${mixin.flexbox({ dir: 'column', vertical: 'center' })};
`;

export const IssueListTop = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  width: 1280px;
  height: 40px;
  margin-bottom: 32px;
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
  ${({ theme }) => theme.fontStyles.linkSmall}
  color: ${({ theme }) => theme.fontColors.gray2};
  gap: 42px;
`;
export const SearchBar = styled.input`
  ${mixin.flexbox({ vertical: 'center' })};
  padding: 6px 24px;
  width: 472px;
  height: 100%;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-radius: 0px 11px 11px 0px;
  background: ${({ theme }) => theme.backgroundColors.gray3};
`;
export const IssueListTopRight = styled.div`
  display: flex;
  gap: 16px;
  height: 100%;
`;
export const Buttons = styled.div`
  display: flex;
  border-radius: 11px;
`;

export const LabelBtn = styled.button`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  width: 160px;
  height: 100%;
  border-radius: 11px 0px 0px 11px;
  ${({ theme }) => theme.fontStyles.linkSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
  gap: 8px;
`;
export const MileStoneBtn = styled(LabelBtn)`
  border-radius: 0px 11px 11px 0px;
`;
export const BtnText = styled.div`
  ${({ theme }) => theme.fontStyles.linkSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
`;
export const BtnContentCount = styled(BtnText)``;
export const IssueOption = styled.div`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
  background: ${({ theme }) => theme.backgroundColors.blue2};
  gap: 4px;
  border-radius: 11px;
  width: 120px;
  height: 100%;
  ${({ theme }) => theme.fontStyles.linkSSmall};
  color: ${({ theme }) => theme.fontColors.gray1};
`;
