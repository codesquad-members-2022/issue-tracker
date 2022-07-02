/* eslint-disable no-unused-vars */
/* eslint-disable jsx-a11y/mouse-events-have-key-events */
/* eslint-disable react/require-default-props */
/* eslint-disable import/no-extraneous-dependencies */
import { useEffect, useRef, useState } from 'react';

import DropBox from '@/components/AssigneeDrop/DropBox';
import Filter from '@/components/Filter';
import useMouse from '@/hooks/useMouse';

import * as S from './style';

interface Item {
  id: number;
  name: string;
  profileImageUrl: string;
}

interface DropDownProps {
  dropDownWidth: string;
  dropDownHeight: string;
  dropBoxWidth?: string | undefined;
  dropDownTitle: string;
  itemsTitle: string;
  items: Item[];
  isStartFromRight?: boolean;
}

export default function DropDown({
  dropDownWidth,
  dropDownHeight,
  dropDownTitle,
  dropBoxWidth = '100%',
  isStartFromRight,
  itemsTitle,
  items,
}: DropDownProps) {
  const {
    isMouseOvered,
    isActive,
    isClicked,
    handleMouseOver,
    handleMouseOut,
    handleMouseDown,
    handleMouseUp,
    handleClick,
  } = useMouse(false);

  const [isClickAway, setIsClickAway] = useState(false);
  const drop = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const handleClickAway = (event: MouseEvent) => {
      setIsClickAway(!drop.current?.contains(event.target as Node));
    };

    document.addEventListener('click', handleClickAway);
  }, []);

  useEffect(() => {
    if (isClickAway && isClicked) {
      handleClick();
    }
  }, [isClickAway, isClicked]);

  return (
    <S.Container width={dropDownWidth} height={dropDownHeight} ref={drop}>
      <Filter
        handleMouseOver={handleMouseOver}
        handleMouseOut={handleMouseOut}
        handleMouseDown={handleMouseDown}
        handleMouseUp={handleMouseUp}
        dropDownTitle={dropDownTitle}
        isMouseOvered={isMouseOvered}
        isActive={isActive}
        handleClick={handleClick}
      />
      <S.Content
        isClicked={isClicked}
        className={dropDownTitle}
        width={dropBoxWidth}
        isStartFromRight={isStartFromRight}
      >
        <DropBox itemsTitle={itemsTitle} items={items} />
      </S.Content>
    </S.Container>
  );
}
