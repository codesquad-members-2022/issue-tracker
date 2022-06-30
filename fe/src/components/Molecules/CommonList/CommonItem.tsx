import React from 'react';
import styled from 'styled-components';

const StyledCommonItem = styled.li`
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px 54px 16px 32px;
`;

const CommonItem = ({ children, style }: { children: React.ReactNode; style?: React.CSSProperties }) => {
  return <StyledCommonItem style={style}>{children}</StyledCommonItem>;
};

export default CommonItem;
