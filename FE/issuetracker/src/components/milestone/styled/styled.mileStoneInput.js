import styled from 'styled-components';

export const inputWrapper = styled.div`
  height: 40px;
  padding: 0 24px;
  border-radius: 11px;
  background: ${({ theme }) => theme.backgroundColors.gray3};
`;

export const input = styled.input.attrs(() => ({ type: 'text' }))`
  ${({ theme }) => theme.fontStyles.textSmall};
  height: 40px;
  color: ${({ theme }) => theme.fontColors.gray5};

  &::placeholder {
    color: ${({ theme }) => theme.fontColors.gray4};
  }
`;

export const inputLabel = styled.label`
  width: 80px;
  height: 40px;
  margin-right: 8px;
`;
