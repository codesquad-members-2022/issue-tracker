import React from 'react';
import styled from 'styled-components';

const AddForm = styled.div`
  width: 100%;
  padding: 32px;
  border: 1px solid ${({ theme }) => theme.colors.line};
  border-radius: 16px;
  background: ${({ theme }) => theme.colors.offWhite};

  h2 {
    margin-bottom: 24px;
    ${({ theme }) => theme.fontStyles.textLarge};
  }
`;

interface CommonFormTypes {
  title: string;
  children: JSX.Element;
  style?: React.CSSProperties;
}

const CommonForm = ({ title, style, children }: CommonFormTypes) => {
  return (
    <AddForm style={style}>
      <h2>{title}</h2>
      {children}
    </AddForm>
  );
};

export default CommonForm;
