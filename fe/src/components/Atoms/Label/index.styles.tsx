import styled, { css } from 'styled-components';
import { LabelTypes } from 'components/Atoms/Label/';

type LabelStyleTypes = Pick<LabelTypes, 'labelStyle' | 'titleColor' | 'backgroundColor'>;

const StyledLabel = styled.div<LabelStyleTypes>`
  display: flex;
  align-items: center;
  width: fit-content;
  padding: 4px 16px;
  border-radius: 30px;
  background: ${({ backgroundColor }) => backgroundColor};
  color: ${({ titleColor }) =>
    titleColor === 'white'
      ? css`
          ${({ theme }) => theme.colors.offWhite};
        `
      : css`
          ${({ theme }) => theme.colors.titleActive};
        `};
  ${({ theme }) => theme.fontStyles.textXSmall};
  cursor: default;

  ${({ labelStyle }) =>
    labelStyle === 'LINE' &&
    css`
      border: 1px solid ${({ theme }) => theme.colors.line};
      background: ${({ backgroundColor }) => backgroundColor};
      color: ${({ theme }) => theme.colors.label};
    `};
`;

export default StyledLabel;
