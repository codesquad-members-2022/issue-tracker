import React from 'react';
import styled from 'styled-components';

const StyledCommonList = styled.ul`
  min-width: 800px;
  border: 1px solid ${({ theme }) => theme.colors.line};
  border-radius: 16px;

  li {
    background: ${({ theme }) => theme.colors.offWhite};
    border-bottom: 1px solid ${({ theme }) => theme.colors.line};

    &:last-child {
      border-bottom: none;
      border-radius: 0px 0px 16px 16px;
    }
  }
`;

const CommonList = ({ children }: { children: React.ReactNode }) => {
  return <StyledCommonList>{children}</StyledCommonList>;
};

export default CommonList;
