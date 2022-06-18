import styled from 'styled-components';
import { mixin } from 'design/GlobalStyles';

export const issueTitleWrapper = styled.div`
  ${mixin.flexbox({ vertical: 'center' })};
  position: relative;
<<<<<<< HEAD
  margin-top: 12px;
=======
>>>>>>> origin/11-feat-fe-newissue-레이아웃-구현
`;

export const issueTitle = styled.div`
  ${({ theme }) => theme.fontStyles.display};
  color: ${({ theme }) => theme.fontColors.gray5};
  margin-right: 16px;
`;

export const issueNumber = styled.div`
  ${({ theme }) => theme.fontStyles.display};
  color: ${({ theme }) => theme.fontColors.gray2};
`;

export const buttonWrapper = styled.div`
  display: inline-flex;
  position: absolute;
  right: 0;
`;
