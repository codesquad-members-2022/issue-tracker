/* eslint-disable react/jsx-no-bind */
import { useRef } from 'react';
import { useNavigate, useSearchParams } from 'react-router-dom';

import useGetIssueData from 'api/issue';

import styled from 'styled-components';
import FilterBar from 'components/Molecules/FilterBar';
import IssueList from 'components/Molecules/IssueList';
import SubNav from 'components/Molecules/SubNav';
import Pagination from 'components/Molecules/Pagination';

import useInput from 'hooks/useInput';
import ISSUE_FILTER from 'helpers/constants/IssueFilter';

const StyledDiv = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;

  & > & {
    min-width: fit-content;
  }

  button {
    margin-left: 16px;
  }

  & + ul {
    margin-top: 24px;
  }
`;

const Issues = () => {
  const inputRef = useRef<HTMLInputElement>(null);

  const [searchParams, setSearchParams] = useSearchParams();
  const page = searchParams.get('page');

  const { isLoading, data, error } = useGetIssueData(Number(page));

  const { onChangeInput, onClickInput, onBlurInput } = useInput();
  const navigate = useNavigate();

  if (isLoading) return <div>loading</div>;
  if (error) return <div>error</div>;
  if (!data) return <div>데이터가 없습니다</div>;

  const HandleOnClick = () => {
    navigate('/issues/new');
  };

  const filterTabs = [
    {
      id: 1,
      dropdownTitle: '담당자 필터',
      dropdownList: data.assignees,
      indicatorLabel: '담당자',
    },
    {
      id: 2,
      dropdownTitle: '레이블 필터',
      dropdownList: data.labels,
      indicatorLabel: '레이블',
    },
    {
      id: 3,
      dropdownTitle: '마일스톤 필터',
      dropdownList: data.milestones,
      indicatorLabel: '마일스톤',
    },
    {
      id: 4,
      dropdownTitle: '작성자 필터',
      dropdownList: data.writers,
      indicatorLabel: '작성자',
    },
  ];

  return (
    <>
      <StyledDiv>
        <FilterBar
          dropdownList={ISSUE_FILTER}
          dropdownTitle="이슈 필터"
          indicatorLabel="Filter"
          indicatorStyle="FILTERBAR"
          inputMaxLength={53}
          inputPlaceholder="Search all issues"
          inputSize="SMALL"
          inputStyle="FILTERBAR"
          inputType="text"
          inputValue="is:open"
          inputRef={inputRef}
          onBlur={onBlurInput}
          onChange={onChangeInput}
          onClick={onClickInput}
          panelType="radio"
        />
        <SubNav labelCount={data.labelCount} milestoneCount={data.milestoneCount} HandleOnClick={HandleOnClick} />
      </StyledDiv>
      <IssueList
        openIssueCount={data.openIssueCount}
        closedIssueCount={data.closedIssueCount}
        issues={data.issues}
        filterTabs={filterTabs}
      />
      <Pagination data={data.issues} page={page} setSearchParams={setSearchParams} />
    </>
  );
};

export default Issues;
