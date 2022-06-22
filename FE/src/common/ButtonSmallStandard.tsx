import React from 'react';
import styled from 'styled-components';
import { COLORS, GREYSCALE } from '@/constants';
import Icon from '@/assets/icons/Icon';

type ButtonSmallStandardProps = {
  isDisabled: boolean;
  label: string;
  onClick: (event: React.MouseEvent<HTMLButtonElement>) => void;
};

function ButtonSmallStandard({
  isDisabled,
  label,
  onClick,
}: ButtonSmallStandardProps) {
  const Box = isDisabled ? ButtonBoxDisabled : ButtonBoxAbled;

  return (
    <Box disabled={isDisabled} onClick={onClick}>
      <Icon iconName="plus" stroke={GREYSCALE.OFF_WHITE} />
      {label}
    </Box>
  );
}

export const ButtonBox = styled.button`
  width: 120px;
  height: 40px;
  background-color: ${COLORS.BLUE};
  color: ${GREYSCALE.OFF_WHITE};
  border-radius: 12px;
  ${({ theme }) => theme.TYPOGRAPHY.LINK_X_SMALL};
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'center', 'center')}
  gap: 4px;
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

export default ButtonSmallStandard;
