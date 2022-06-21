import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const MileStoneWrap = styled.div<{ idx: number }>`
  width: 100%;
  height: 100px;
  ${mixin.flexbox({ dir: 'column', horizontal: 'center' })};
  gap: 8px;
  padding: 0 32px;
  background: ${({ theme }) => theme.backgroundColors.gray1};
  border-top: 1px solid ${({ theme }) => theme.backgroundColors.gray4};
`;

export const MileStoneTop = styled.div`
  width: 100%;
  ${mixin.flexbox({ dir: 'column', horizontal: 'center', vertical: 'space-between' })};
`;
export const MileStoneTopLeft = styled.div`
  ${mixin.flexbox({ vertical: 'center' })};
  gap: 10px;
  height: 32px;
`;
export const MileStoneIconFigure = styled.figure`
  color: ${({ theme }) => theme.fontColors.blue};
`;
export const Title = styled.div`
  ${({ theme }) => theme.fontStyles.linkMedium};
  color: ${({ theme }) => theme.fontColors.gray5};
`;
export const DueDate = styled.div`
  ${({ theme }) => theme.fontStyles.textSmall};
  color: ${({ theme }) => theme.fontColors.gray2};
  ${mixin.flexbox({ horizontal: 'space-between', vertical: 'center' })};
  gap: 10px;
`;
export const MileStoneTopRight = styled.div``;
