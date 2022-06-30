import styled, { DefaultTheme } from 'styled-components';
// TODO: 스타일 값 매직넘버 상수화
// TODO: 이부분 section이라고 보는게 맞을까?
export const ControlPanel = styled.section`
  width: 100%;
`;

type FilterBarProps = {
  theme: DefaultTheme;
};

export const FilterBar = styled.div<FilterBarProps>`
  display: flex;
  align-items: center;
  width: 600px;
  height: 40px;
  border: 1px solid ${({ theme }) => theme.color.line};
  border-radius: 11px;
  background-color: ${({ theme }) => theme.color.inputBackground};
`;

export const FilterDetail = styled.details`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 20%;
  border-right: 1px solid ${({ theme }) => theme.color.line};
  height: inherit;
  gap: 8px;
  position: relative;

  summary {
    box-sizing: border-box; // INFO: 패딩 안 넘어가게
    cursor: pointer;
    background-color: ${({ theme }) => theme.color.offwhite};
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: inherit;
    height: 100%;
    padding: 6px 24px;
    border-top-left-radius: 11px;
    border-bottom-left-radius: 11px;
  }
`;

export const FilterForm = styled.form`
  display: flex;
  align-items: center;
  width: 80%;
  height: inherit;
  padding: 6px 24px;
  box-sizing: border-box;
`;

export const FilterInput = styled.input`
  border: none;
  background-color: ${({ theme }) => theme.color.inputBackground};
  //TODO: 리팩토링 가능할듯?
  font-size: 16px;
  margin-left: 11px;

  :focus {
    outline: none;
  }
`;

export const DetailMenu = styled.div`
  position: absolute;
  width: 240px;
  border: 1px solid ${({ theme }) => theme.color.line};
  border-radius: 16px;
  left: 0;
  top: 120%;
  overflow: hidden;
  background-color: ${({ theme }) => theme.color.offwhite};

  & > :first-child {
    border-top: none;
  }
`;

export const Detailheader = styled.div`
  display: flex;
  align-items: center;
  width: inherit;
  height: 48px;
  padding: 8px 16px;
  border-bottom: 1px solid ${({ theme }) => theme.color.line};
  background-color: ${({ theme }) => theme.color.background};
`;

export const DetailList = styled.ul`
  & :last-child {
    border-bottom: none;
  }
`;

export const DetailListItem = styled.li`
  display: flex;
  align-items: center;
  width: inherit;
  padding: 8px 16px;
  background-color: ${({ theme }) => theme.color.offwhite};
  border-bottom: 1px solid ${({ theme }) => theme.color.line};
`;
