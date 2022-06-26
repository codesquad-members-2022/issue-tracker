import FilterBar from 'common/Filter/FilterBar';
import * as S from './Issues.styled';
import { filterList } from './Constants';

function Filter() {
  return (
    <S.Filter>
      <FilterBar filterTitle="필터" filterList={filterList} />
      <S.SearchForm type="text" defaultValue="is:Open" />
    </S.Filter>
  );
}

export default Filter;
