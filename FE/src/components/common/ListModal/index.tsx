import * as S from './style';
import { Text } from '@/components/common/Text';

export type ListModalProps = {
  listTitle: string;
  rightGap?: string;
  children: React.ReactNode;
};

// TODO:데이터를 받아서 title 부분과, map 돌려서 ListItem 생성
export function ListModal({ listTitle, rightGap, children }: ListModalProps) {
  return (
    <S.ListModal rightGap={rightGap}>
      <S.ListTitle>
        <Text text={`${listTitle}`} fontSize="large" />
      </S.ListTitle>
      {children}
    </S.ListModal>
  );
}
