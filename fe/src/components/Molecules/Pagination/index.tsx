import React from 'react';
import Button from 'components/Atoms/Button';
import { StyledPageButton, StyledPagination } from './index.styles';

interface DataTypes {
  content: any;
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageable: {};
  size: number;
  sort: {};
  totalElements: number;
  totalPages: number;
}

const Pagination = ({
  data,
  page,
  setSearchParams,
}: {
  data: DataTypes;
  page: string | null;
  setSearchParams: any;
}) => {
  const handlePaginationClick = (event: React.MouseEvent<HTMLButtonElement>) => {
    if (!(event.target instanceof HTMLButtonElement)) return;
    const clickedPage = event.target.dataset.id;
    const pageParams = Number(clickedPage) - 1;

    setSearchParams({ page: pageParams! });
  };

  const handleClickPriviousButton = () => {
    setSearchParams({ page: Number(page) - 1 });
  };

  const handleClickNextButton = () => {
    setSearchParams({ page: Number(page) + 1 });
  };

  const isFocus = (id: number) => Number(page) === id;

  const pageButtons = [...Array(data.totalPages)].map((el, index) => {
    return (
      <StyledPageButton
        // eslint-disable-next-line react/no-array-index-key
        key={`pageButton-${index}`}
        data-id={index + 1}
        onClick={handlePaginationClick}
        focus={isFocus(index)}
      >
        {index + 1}
      </StyledPageButton>
    );
  });

  return (
    <StyledPagination>
      <Button
        buttonStyle="NO_BORDER"
        label="< PREVIOUS"
        size="SMALL"
        disabled={data.first}
        HandleOnClick={handleClickPriviousButton}
      />
      {pageButtons}
      <Button
        buttonStyle="NO_BORDER"
        label="NEXT >"
        size="SMALL"
        disabled={data.last}
        HandleOnClick={handleClickNextButton}
      />
    </StyledPagination>
  );
};

export default React.memo(Pagination);
