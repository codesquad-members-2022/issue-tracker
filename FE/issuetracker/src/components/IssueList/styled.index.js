import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

<<<<<<< HEAD
export const IssueListWrap = styled.div``;
=======
export const IssueListWrap = styled.div`
  ${mixin.flexbox({ dir: 'column', vertical: 'center' })};
`;
>>>>>>> 61d6b972aae07b29907b94fa937dd6eaeb394cdf

export const IssueListTop = styled.div`
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  width: 1280px;
  height: 40px;
<<<<<<< HEAD
=======
  margin-bottom: 32px;
>>>>>>> 61d6b972aae07b29907b94fa937dd6eaeb394cdf
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
<<<<<<< HEAD
`;
export const SearchBar = styled.input`
=======
  ${({ theme }) => theme.fontStyles.linkSmall}
  color: ${({ theme }) => theme.fontColors.gray2};
`;
export const SearchBar = styled.input`
  ${mixin.flexbox({ vertical: 'center' })};
>>>>>>> 61d6b972aae07b29907b94fa937dd6eaeb394cdf
  padding: 6px 24px;
  width: 472px;
  height: 100%;
  border: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
  border-radius: 0px 11px 11px 0px;
<<<<<<< HEAD
`;
export const IssueListTopRight = styled.div``;
export const Buttons = styled.div``;

export const LabelButton = styled.button`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
=======
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
`;
export const MileStoneBtn = styled(LabelBtn)`
  border-radius: 0px 11px 11px 0px;
`;
export const IssueOption = styled.div`
  ${mixin.flexbox({ horizontal: 'center', vertical: 'center' })};
  background: ${({ theme }) => theme.backgroundColors.blue2};
  gap: 4px;
  border-radius: 11px;
  width: 120px;
  height: 100%;
  ${({ theme }) => theme.fontStyles.linkSSmall};
  color: ${({ theme }) => theme.fontColors.gray1};
>>>>>>> 61d6b972aae07b29907b94fa937dd6eaeb394cdf
`;
