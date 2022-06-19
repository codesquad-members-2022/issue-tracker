import * as S from './style';
import UNDER_ARROW from '@/assets/UnderArrow.svg';
import { Icon } from '@/components/common/Icon';
import SEARCH_ICON from '@/assets/Search.svg';
import { Text } from '@/components/common/Text';

// TODO: 필터 버튼 네이밍 고민
export function ControlPanel() {
  return (
    <S.ControlPanel>
      <S.FilterBar>
        <S.FilterDetail>
          <summary>
            <Text text="필터" fontWeight="bold" color="placeholder" />
            <Icon iconName={UNDER_ARROW} iconSize="base" />
          </summary>
        </S.FilterDetail>
        {/* <S.FilterPopUp>
          <FilterList />
          <FilterList />
          <FilterList />
        </S.FilterPopUp> */}
        <S.FilterForm>
          <Icon iconName={SEARCH_ICON} iconSize="base" />
          <S.FilterInput placeholder="Search All Issue" />
        </S.FilterForm>
      </S.FilterBar>
    </S.ControlPanel>
  );
}
