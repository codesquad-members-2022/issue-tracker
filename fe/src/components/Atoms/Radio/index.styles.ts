import styled from 'styled-components';
import checkOffCircle from 'assets/icons/checkOffCircle.svg';
import checkOnCircle from 'assets/icons/checkOnCircle.svg';

export const StyledRadioForm = styled.form`
  display: flex;
  align-items: center;
  width: fit-content;
  height: 40px;
  padding: 0px 24px;
  border-radius: 12px;
  border: 1px solid ${({ theme }) => theme.colors.inputBackground};
  background: ${({ theme }) => theme.colors.inputBackground};

  span {
    width: 60px;
    color: ${({ theme }) => theme.colors.label};
    ${({ theme }) => theme.fontStyles.textXSmall};
  }

  div {
    display: flex;
    align-items: center;
  }
`;

export const StyledRadioOption = styled.div`
  input {
    margin: 0 10px;
  }

  label {
    display: flex;
    align-items: center;

    span {
      display: inline-block;
      width: 80px;
      ${({ theme }) => theme.fontStyles.textSmall};
      color: ${({ theme }) => theme.colors.titleActive};
    }
  }
`;
