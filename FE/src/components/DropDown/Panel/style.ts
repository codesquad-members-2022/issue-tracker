import styled from 'styled-components';
import { I$PanelProps, I$SelectedItemProps } from '@/components/Dropdown/Panel/type';

const $Panel = styled.div<I$PanelProps>`
  position: absolute;
  top: ${({ top }) => `calc(32px + ${top})`};
  left: ${({ left }) => (left === 'left' ? 0 : 'auto')};
  right: ${({ left }) => (left === 'right' ? 0 : 'auto')};
  min-width: ${({ width }) => (width ? width : '240px')};
  width: fit-content;
  font-size: ${({ theme }) => theme.FONT.SIZE.X_SMALL};
  border: ${({ theme }) => `1px solid ${theme.COLOR.line}`};
  border-radius: 16px;
  overflow: hidden;
  z-index: 10;
`;

const $PanelTitle = styled.label`
  display: flex;
  align-items: center;
  height: 48px;
  padding: 0 16px;
  font-size: ${({ theme }) => theme.FONT.SIZE.MEDIUM};
  background: ${({ theme }) => theme.COLOR.background};
`;

const $Select = styled.select`
  position: absolute;
  top: -100%;
  left: -100%;
  visibility: hidden;
`;

const $SelectList = styled.ul`
  background: ${({ theme }) => theme.PALETTE.WHITE};
`;

const $SelectedItem = styled.li<I$SelectedItemProps>`
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 8px;
  height: 44px;
  padding: 0 16px;
  font-size: ${({ theme }) => theme.FONT.SIZE.BASE};
  color: ${({ theme, selected }) => (selected ? 'inherit' : theme.PALETTE.GREY[100])};
  border-top: ${({ theme }) => `1px solid ${theme.COLOR.line}`};
`;

export { $Panel, $PanelTitle, $Select, $SelectList, $SelectedItem };
