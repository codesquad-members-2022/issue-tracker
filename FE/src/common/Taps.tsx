import React from 'react';
import styled from 'styled-components';
import { COLORS, GREYSCALE } from '@/constants';
import Tap from './Tap';

function Taps() {
  return (
    <TapsBox>
      <Tap isActive={false} iconName="tag" title="레이블" count={0} />
      <span></span>
      <Tap isActive={false} iconName="milestone" title="마일스톤" count={0} />
    </TapsBox>
  );
}

const TapsBox = styled.div`
  ${({ theme }) => theme.LAYOUT.flexLayoutMixin('row')}
  border-radius: 12px;
  border: 1px solid ${GREYSCALE.LINE};
  overflow: hidden;

  span {
    width: 1px;
    background-color: ${GREYSCALE.LINE};
  }
`;

export default Taps;
