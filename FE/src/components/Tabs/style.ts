import { $Button } from '@/components/Button/style';
import { COLOR } from '@/styles/common';
import styled, { css } from 'styled-components';

const $Tab = styled.ul`
  display: flex;
  width: fit-content;
  border: 1px solid ${({ theme }) => theme.COLOR.line};
  border-radius: 11px;
  overflow: hidden;
`;

const $TabItem = styled.li`
  display: flex;
  justify-content: center;
  align-items: center;
  min-width: 160px;
  height: 40px;
  background-color: ${({ theme }) => theme.COLOR.background};
  :not(:last-child) {
    border-right: 1px solid ${({ theme }) => theme.COLOR.line};
  }
`;

export { $Tab, $TabItem };
