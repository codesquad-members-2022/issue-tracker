import { useState } from 'react';

import { useGetLabelData } from 'api/label';
import useGetMilestoneData from 'api/milestone';

import styled from 'styled-components';
import SubNav from 'components/Molecules/SubNav';
import LabelList from 'components/Molecules/LabelList/index';
import CommonForm from 'components/Molecules/CommonForm';
import LabelForm from 'components/Organisms/LabelForm';

const StyledLabelList = styled.div`
  & > div:first-child {
    margin-bottom: 24px;
  }
`;

const LabelPage = () => {
  const { isLoading: labelsDataIsLoading, data: labelsData, error: labelsDataError } = useGetLabelData();
  const {
    isLoading: milestonesDataIsLoading,
    data: milestonesData,
    error: milestonesDataError,
  } = useGetMilestoneData();

  const [isAddLabel, setIsAddLabel] = useState(false);
  const focusAddLabel = () => setIsAddLabel((focus) => !focus);

  if (labelsDataIsLoading || milestonesDataIsLoading) return <div>loading</div>;
  if (labelsDataError || milestonesDataError) return <div>error</div>;
  if (!labelsData || !milestonesData) return <div>데이터가 없습니다</div>;

  const milestoneCount = milestonesData.milestones.length;

  return (
    <StyledLabelList>
      <SubNav
        labelCount={labelsData.labels.length}
        milestoneCount={milestoneCount}
        type={isAddLabel ? 'CLOSE' : 'ADD'}
        HandleOnClick={focusAddLabel}
      />
      {isAddLabel && (
        <CommonForm title="새로운 레이블 추가" style={{ marginBottom: '24px' }}>
          <LabelForm mode="ADD" setIsAddLabel={setIsAddLabel} />
        </CommonForm>
      )}
      <LabelList labelData={labelsData.labels} />
    </StyledLabelList>
  );
};

export default LabelPage;
