import { useState } from 'react';
import Button from '@/components/Button';
import { Icon } from '@/components/Icon';

interface IDropDown {
  title: string;
}

export default function DropDown({ title }: IDropDown) {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <Button styleType="mediumText" onClick={() => setIsOpen(!isOpen)}>
      {title}
      {isOpen ? <Icon IconType="arrowUp" /> : <Icon IconType="arrowDown" />}
    </Button>
  );
}
