/* eslint-disable no-unused-vars */
import DropDown from '@/components/DropDown';

import * as S from './style';

interface Item {
  filter: string;
  description: string;
}

interface FilterSeatchBarProps {
  dropDownWidth: string;
  dropDownHeight: string;
  dropBoxWidth?: string | undefined;
  dropDownTitle: string;
  itemsTitle: string;
  items: Item[];
}

export default function FilterSeatchBar({
  dropDownWidth,
  dropDownHeight,
  dropDownTitle,
  dropBoxWidth,
  itemsTitle,
  items,
}: FilterSeatchBarProps) {
  return (
    <S.FilterSearchBarContainer>
      <DropDown
        dropDownWidth={dropDownWidth}
        dropDownHeight={dropDownHeight}
        dropBoxWidth={dropBoxWidth}
        dropDownTitle={dropDownTitle}
        itemsTitle={itemsTitle}
        items={items}
      />
      <S.SearchBar
        type="text"
        placeholder="is:issue is:open"
        height={dropDownHeight}
      />
    </S.FilterSearchBarContainer>
  );
}
