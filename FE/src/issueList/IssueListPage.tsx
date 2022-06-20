import React from 'react';
import FilterBar from '@/common/FilterBar';
import Taps from '@/common/Taps';
import ButtonSmallStandard from '@/common/ButtonSmallStandard';

function IssueListPage() {
  return (
    <div>
      <FilterBar />
      <Taps />
      <ButtonSmallStandard
        isDisabled={false}
        label="이슈 작성"
        onClick={() => console.log('click 이슈작성버튼')}
      />
    </div>
  );
}

export default IssueListPage;
