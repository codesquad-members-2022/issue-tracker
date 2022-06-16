import React from 'react';
import styled from 'styled-components';
import { COLORS, GREYSCALE } from '@/constants';

type ButtonProps = {
  isDisabled: boolean;
  label: string;
  clickHandler: (event: React.MouseEvent<HTMLButtonElement>) => void;
};

function Button({ isDisabled, label, clickHandler }: ButtonProps) {
  const Box = isDisabled ? ButtonBoxDisabled : ButtonBoxAbled;

  return (
    <Box disabled={isDisabled} onClick={clickHandler}>
      {label}
    </Box>
  );
}

export const ButtonBox = styled.button`
  width: 340px;
  height: 64px;
  background-color: ${COLORS.BLUE};
  color: ${GREYSCALE.OFF_WHITE};
  border-radius: 20px;
  ${({ theme }) => theme.TYPOGRAPHY.LINK_MEDIUM};
`;

const ButtonBoxAbled = styled(ButtonBox)`
  &:hover {
    background-color: ${COLORS.DARK_BLUE};
  }

  &:focus {
    box-shadow: 0 0 0 4px ${COLORS.LIGHT_BLUE};
  }
`;

const ButtonBoxDisabled = styled(ButtonBox)`
  opacity: 0.5;
`;

export default Button;
