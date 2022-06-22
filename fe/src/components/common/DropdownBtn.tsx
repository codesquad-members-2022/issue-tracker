import { useState } from 'react';
import styled from 'styled-components';
import { COLOR } from 'styles/color';
import FONT from 'styles/font';
import SIZE from 'styles/size';
import Icon from './Icon';
import Modal from './Modal';

function DropdownBtn({
  name,
  children,
  startLocation,
  gap = SIZE.DROPDOWN_BTN.GAP,
  width = 'fit-content',
  height = `${SIZE.DROPDOWN_BTN.HEIGHT}px`,
  className,
}: DropdownBtnProps) {
  const [isModalActive, setIsModalActive] = useState(false);

  return (
    <Wrapper width={width} height={height} className={className}>
      <Button onClick={handleClickedBtn} gap={gap}>
        {name}
        <Icon
          icon="down"
          width={SIZE.DOWN_BTN.WIDTH}
          height={SIZE.DOWN_BTN.HEIGHT}
          stroke="inherit"
        />
      </Button>
      <Child startLocation={startLocation}>
        {isModalActive && <Modal setIsModalActive={setIsModalActive}>{children}</Modal>}
      </Child>
    </Wrapper>
  );

  function handleClickedBtn() {
    setIsModalActive(!isModalActive);
  }
}

const Wrapper = styled.div<{ width: string; height: string }>`
  position: relative;
  display: flex;
  align-items: center;
  flex-direction: column;
  width: ${({ width }) => width};
  height: ${({ height }) => height};
`;

const Button = styled.button<{ gap: number }>`
  display: flex;
  align-items: center;
  gap: ${({ gap }) => gap}px;
  width: fit-content;
  height: 100%;
  border: 0;
  background-color: transparent;
  font-size: ${FONT.SIZE.SMALL};
  font-weight: ${FONT.WEIGHT.BOLD};
  color: ${COLOR.GREY[500]};
  stroke: ${COLOR.GREY[500]};
  &:hover {
    color: ${COLOR.GREY[600]};
    stroke: ${COLOR.GREY[600]};
  }
`;

const Child = styled.div<ChildProps>`
  width: fit-content;
  position: absolute;
  top: 90%;
  right: ${({ startLocation }) => startLocation === 'RIGHT' && 0};
  left: ${({ startLocation }) => startLocation === 'LEFT' && 0};
`;

type ChildProps = Pick<DropdownBtnProps, 'startLocation'>;

type DropdownBtnProps = {
  name: string;
  children: React.ReactNode;
  startLocation: 'LEFT' | 'RIGHT';
  gap?: number;
  width?: string;
  height?: string;
  className?: string;
};

export default DropdownBtn;
