import React from "react";
import { Link } from "react-router-dom";
import styled, { css } from "styled-components";

import { FilterBar as FilterInput } from "components/common/filterBar/FilterBar";
import Button from "components/common/button/Button";
import PlusIcon from "components/Icons/Plus";
import { Tab as TabBar } from "../common/Tap";

const counts = {
  label: 2,
  mileStone: 3,
};

function IssueListPage() {
  return (
    <>
      <TabBar counts={counts} />
      <Link to="/addIssue">
        <Button size="small" icon={<PlusIcon />} text="이슈 작성" />
      </Link>
      {/* 기본 컴포넌트 구성 
      <TabBar></TabBar>
      <IssueList>
      <ListFilter></ListFilter>
      <IssueCards>
      <IssuCard></IssuCard>
      </IssueCards>
    </IssueList> */}
    </>
  );
}

export default IssueListPage;
