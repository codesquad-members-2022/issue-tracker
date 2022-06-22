import * as S from './Issues.styled';

function Filter() {
  return (
    <S.Filter>
      <S.FilterBar>
        <p>필터</p>
        <img src="icons/filterIcon.svg" alt="filterIcon" />
      </S.FilterBar>
      <S.SearchForm type="text" />
    </S.Filter>
  );
}

export default Filter;
