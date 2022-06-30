import styled from 'styled-components';

export const StyledLabelForm = styled.div`
  display: flex;

  & > div:first-child {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 20%;
    margin-right: 24px;
  }
`;

export const StyledLabelInfo = styled.div`
  width: 80%;

  & > form:first-child,
  & > form:nth-child(2) {
    width: 100%;
    margin-bottom: 16px;
  }
`;
export const ColorOptionWrapper = styled.div`
  display: flex;
  align-items: center;
  margin-bottom: 24px;

  & > form:first-child {
    margin-right: 16px;
  }
`;

export const ButtonWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: flex-end;
  gap: 8px;
`;
