import React from 'react';
import styled from 'styled-components';

const StyledCommonTitle = styled.div`
  display: flex;
  justify-content: space-between;
  background: #f7f7fc;
  padding: 18px 40px 18px 32px;
  border-radius: 16px 16px 0px 0px;
  border-bottom: 1px solid ${({ theme }) => theme.colors.line};
`;

const CommonTitle = ({ children, style }: { children: React.ReactNode; style?: React.CSSProperties }) => {
  return <StyledCommonTitle style={style}>{children}</StyledCommonTitle>;
};

export default CommonTitle;
