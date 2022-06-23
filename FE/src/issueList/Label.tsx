import { GREYSCALE } from '@/constants';
import React from 'react';
import styled from 'styled-components';

type LabelProps = {
  labelName: string;
  backgroundColor: string;
  textColor: string;
};

type LabelBoxProps = {
  backgroundColor: string;
  color: string;
};

function Label({ labelName, backgroundColor, textColor }: LabelProps) {
  const color =
    textColor === '어두운 색' ? GREYSCALE.TITLE_ACTION : GREYSCALE.OFF_WHITE;
  return (
    <LabelBox backgroundColor={backgroundColor} color={color}>
      {labelName}
    </LabelBox>
  );
}

const LabelBox = styled.li<LabelBoxProps>`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row', 'center', 'center')}
  padding: 4px 16px;
  background-color: ${({ backgroundColor }) => backgroundColor};
  border-radius: 30px;
  color: ${({ color }) => color};
  ${({ theme }) => theme.TYPOGRAPHY.TEXT_X_SMALL}
  cursor: pointer;
`;

export default Label;
