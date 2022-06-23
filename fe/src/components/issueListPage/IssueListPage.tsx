import React from "react";
import styled, { css } from "styled-components";

import { FilterBar as FilterInput } from "components/common/filterBar/FilterBar";
import { Tab as TabBar } from "../common/Tap";

const counts = {
  label: 2,
  mileStone: 3,
};

function IssueListPage() {
  return (
    <>
      <TabBar counts={counts} />
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
