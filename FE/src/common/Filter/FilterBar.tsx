import * as S from '../../pages/Issues/Issues.styled';

interface FilterType {
  filterTitle: string;
  filterList: { title: string; menus: string[] };
}

function FilterBar({ filterTitle, filterList }: FilterType) {
  return (
    <S.FilterBar>
      <details>
        <S.DetailWrapper>
          <p>{filterTitle}</p>
          <img src="icons/filterIcon.svg" alt="filterIcon" />
        </S.DetailWrapper>
        <S.DetailContent position="top">
          <S.IssueFilter>{filterList.title}</S.IssueFilter>
          {filterList.menus.map(menu => (
            <S.FilterTest key={menu}>
              <p>{menu}</p>
              <input type="checkbox" />
            </S.FilterTest>
          ))}
        </S.DetailContent>
      </details>
    </S.FilterBar>
  );
}

export default FilterBar;
