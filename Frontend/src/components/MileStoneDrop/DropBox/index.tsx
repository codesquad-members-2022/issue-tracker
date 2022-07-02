/* eslint-disable no-unused-vars */
import * as S from './style';

interface Item {
  id: number;
  title: string;
  deadline: string;
}

interface DropBoxProps {
  itemsTitle: string;
  items: Item[];
}

export default function DropBox({ itemsTitle, items }: DropBoxProps) {
  const handleClickDropDown = () => {};

  return (
    <S.ItemContainer>
      {itemsTitle}
      {items.map(({ title }) => (
        <S.Item
          key={`issue-filter-${title}`}
          onClick={() => handleClickDropDown}
        >
          {title}
        </S.Item>
      ))}
    </S.ItemContainer>
  );
}
