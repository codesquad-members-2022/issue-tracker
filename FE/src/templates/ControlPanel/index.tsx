import * as S from './style';
import UNDER_ARROW from '@/assets/UnderArrow.svg';
import SEARCH_ICON from '@/assets/Icons/search.svg';
import { Text } from '@/components/common/Text';
import { ListModal } from '@/components/common/ListModal';
import { MilestoneSideBarDetailListItem } from '@/components/Milestone/MilestoneSideBarDetailListItem';

// TODO: 필터 버튼 네이밍 고민
export function ControlPanel() {
  return (
    <S.ControlPanel>
      <S.FilterBar>
        <S.FilterDetail>
          <summary>
            <Text text="필터" fontWeight="bold" color="placeholder" />
            <UNDER_ARROW />
          </summary>
          <ListModal listTitle="이슈 필터" rightGap="-100px">
            {[
              '열린 이슈',
              '내가 작성한 이슈',
              '나에게 할당된 이슈',
              '내가 댓글을 남근 이슈',
              '닫힌 이슈',
            ].map(title => (
              <MilestoneSideBarDetailListItem
                title={title}
                key={`${title}${Date.now()}`}
              />
            ))}
          </ListModal>
        </S.FilterDetail>

        <S.FilterForm>
          <SEARCH_ICON />
          <S.FilterInput placeholder="Search All Issue" />
        </S.FilterForm>
      </S.FilterBar>
    </S.ControlPanel>
  );
}
