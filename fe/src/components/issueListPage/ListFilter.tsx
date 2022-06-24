/* eslint-disable no-nested-ternary */
import React, { useEffect, useState } from "react";
import styled, { css } from "styled-components";

import { v1 } from "uuid";
import CheckBoxInitialIcon from "components/Icons/CheckBoxInitial";
import AlertCircleIcon from "components/Icons/AlertCircle";
import ArchiveIcon from "components/Icons/Archive";
import CheckArrowIcon from "components/Icons/CheckArrow";
import { atom, selector, useRecoilState, useRecoilValue } from "recoil";
import { fakeDB } from "mocks/issueList";
import { FilterBar } from "components/common/filterBar/FilterBar";

const filterArr = ["담당자", "레이블", "마일스톤", "작성자"];

interface TextProps {
  isOpen: boolean;
}

interface IssueList {
  isClose: boolean;
  assignee: string;
  label: string;
  milestone: string;
  writer: string;
}

const issueListState = atom<IssueList[]>({
  key: "issueListState",
  default: fakeDB,
});

const issueListFilterState = atom<string>({
  key: "issueListFilterState",
  default: "show All",
});

function ListFilter() {
  const [issueList, setIssueList] = useRecoilState(issueListState);
  const [filter, setFilter] = useRecoilState(issueListFilterState);
  const [isOpen, setIsOpen] = useState(true);

  const filteredIssueListState = selector({
    key: `filteredIssueListState/${v1()}`,
    get: ({ get }) => {
      const filter = get(issueListFilterState);
      const list = get(issueListState);

      switch (filter) {
        case "Show Opened":
          return list.filter((issue) => !issue.isClose);
        case "Show Closed":
          return list.filter((issue) => issue.isClose);
        case "Assignee Name": // 실제 assignee 이름이 setFilter로 들어감
          return list.filter((issue) => issue.assignee === "Assignee Name");
        case "Label Title":
          return list.filter((issue) => issue.label === "Label Title");
        case "Milestone Title":
          return list.filter((issue) => issue.milestone === "Milestone Title");
        case "Writer Name":
          return list.filter((issue) => issue.writer === "Writer Name");
        default:
          return list;
      }
    },
  });
  const filteredIssues = useRecoilValue(filteredIssueListState);

  const onOpenClick = () => {
    setFilter("Show Opened");
    setIsOpen(true);
  };

  const onCloseClick = () => {
    setFilter("Show Closed");
    setIsOpen(false);
  };
  useEffect(() => {
    console.log(filteredIssues);
  }, [filter]);

  interface IInit {
    text: string;
  }

  return (
    <ListFilterWrapper>
      <IssueType>
        <CheckBoxInitialIcon />
        <OpenedIssue onClick={onOpenClick}>
          <AlertCircleIcon />
          <Text isOpen={isOpen}>열린 이슈(2)</Text>
        </OpenedIssue>
        <ClosedIssue onClick={onCloseClick}>
          <ArchiveIcon />
          <Text isOpen={!isOpen}>닫힌 이슈(0)</Text>
        </ClosedIssue>
      </IssueType>
      <FiltersWrapper>
        {filterArr.map((title) => {
          // const init: any = [];
          const asignees = fakeDB.reduce((acc, issue) => {
            if (!acc.some((el) => el.text === `${issue.assignee}`)) {
              acc.push({ text: `${issue.assignee}` });
            }
            return acc;
          }, [] as any);
          const labels = fakeDB.reduce((acc, issue) => {
            if (!acc.some((el) => el.text === `${issue.label}`)) {
              acc.push({ text: `${issue.label}` });
            }
            return acc;
          }, [] as any);
          const milestone = fakeDB.reduce((acc, issue) => {
            if (!acc.some((el) => el.text === `${issue.milestone}`)) {
              acc.push({ text: `${issue.milestone}` });
            }
            return acc;
          }, [] as any);
          const writers = fakeDB.reduce((acc, issue) => {
            if (!acc.some((el) => el.text === `${issue.writer}`)) {
              acc.push({ text: `${issue.writer}` });
            }
            return acc;
          }, [] as any);

          const items =
            title === "담당자"
              ? asignees
              : title === "레이블"
              ? labels
              : title === "마일스톤"
              ? milestone
              : title === "작성자"
              ? writers
              : "";

          return (
            // <FilterCard key={title}>
            //   <Text isOpen={false}>{title}</Text>
            //   <CheckArrowIcon />
            // </FilterCard>
            <FilterBar
              menuList={{
                title: `${title} 필터`,
                items: [...items],
              }}
              inputDisplay="none"
              text={title}
            />
          );
        })}
      </FiltersWrapper>
    </ListFilterWrapper>
  );
}

const FilterCard = styled.div`
  display: flex;
  align-items: center;
`;

const FiltersWrapper = styled.div`
  display: flex;
  // width: 30%;
  margin-left: 100px;
  justify-content: space-between;
`;

// props로 컬러 받기
const Text = styled.p<TextProps>`
  ${({ theme: { fontSize, fontWeight, colors }, isOpen }) => {
    const fontColor = isOpen ? colors.titleActive : colors.label;
    return css`
      font-weight: ${fontWeight.bold};
      font-size: ${fontSize.small};
      color: ${fontColor};
      line-height: 28px;
      padding: 0 5px;
    `;
  }}
`;

const ListFilterWrapper = styled.div`
  width: 1280px;
  height: 64px;
  background: ${({ theme: { colors } }) => colors.background};
  border-radius: 16px 16px 0px 0px;
  border: 1px solid ${({ theme: { colors } }) => colors.line};
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 32px;
`;

const IssueType = styled.div`
  display: flex;
  width: 23%;
  justify-content: space-between;
  align-items: center;
`;

const OpenedIssue = styled.div`
  display: flex;
  align-items: center;
  cursor: pointer;
`;

const ClosedIssue = styled(OpenedIssue)``;

export default ListFilter;
