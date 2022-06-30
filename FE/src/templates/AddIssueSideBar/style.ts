import styled from 'styled-components';
import { ListModal } from '@/components/common/ListModal/style';

export const OptionSideBar = styled.div`
  width: 310px;
  border: 1px solid ${({ theme }) => theme.color.line};
  border-radius: 16px;
  height: fit-content;

  & > :last-child {
    border-bottom: none;
    border-bottom-left-radius: inherit;
    border-bottom-right-radius: inherit;
  }

  & > :first-child {
    border-top-left-radius: inherit;
    border-top-right-radius: inherit;
  }
`;

export const OptionDetail = styled.details`
  background-color: ${({ theme }) => theme.color.offwhite};
  border-bottom: 1px solid ${({ theme }) => theme.color.line};

  cursor: pointer;
  position: relative;

  summary {
    display: flex;
    flex-direction: column;

    & > :first-child {
      margin-top: 18px;
    }

    & > :last-child {
      margin-bottom: 18px;
    }
  }
`;

export const titleSummary = styled.h4`
  display: flex;
  justify-content: space-between;
  width: inherit;
  height: inherit;
  padding: 34px 32px;
  box-sizing: border-box;
`;

export const OptionMenu = styled.div`
  top: 70%;
  right: 10%;
  position: absolute;
  z-index: 100;
  width: 240px;
  border-radius: 16px;
  border: 1px solid ${({ theme }) => theme.color.line};
  overflow: hidden;
`;

export const OptionHeader = styled.div`
  padding: 8px 16px;
  border-bottom: 1px solid ${({ theme }) => theme.color.line};
  background-color: ${({ theme }) => theme.color.background};
  cursor: default;
`;

export const OptionList = styled.ul`
  background-color: ${({ theme }) => theme.color.offwhite};

  & :last-child {
    border-bottom: none;
  }
`;

export const OptionItem = styled.li`
  padding: 8px 16px;
  border-bottom: 1px solid ${({ theme }) => theme.color.line};
  cursor: pointer;
  :hover {
    background-color: rgba(0, 0, 0, 0.1);
  }
`;

export const StyledListModal = styled(ListModal)`
  right: 10px;
`;
