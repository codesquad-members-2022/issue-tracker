/* eslint-disable no-unused-vars */
import UserImg from '@/components/UserImg';

import * as S from './style';

interface Item {
  id: number;
  name: string;
  profileImageUrl: string;
}

interface DropBoxProps {
  itemsTitle: string;
  items: Item[];
}

export default function DropBox({ itemsTitle, items }: DropBoxProps) {
  const handleClickDropDown = () => {};

  return (
    <S.Container>
      {itemsTitle}
      {items.map(({ name, profileImageUrl }) => (
        <S.Item key={`issue-filter-${name}`} onClick={handleClickDropDown}>
          <S.ItemContainer>
            <UserImg
              img={profileImageUrl}
              size="medium"
              alt={`issue-filter-${profileImageUrl}`}
            />
            <S.Title>{name}</S.Title>
          </S.ItemContainer>
        </S.Item>
      ))}
    </S.Container>
  );
}
