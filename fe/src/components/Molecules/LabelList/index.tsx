import styled from 'styled-components';

import { LabelTypes } from 'components/Atoms/Label';

import CommonList from 'components/Molecules/CommonList/';
import CommonTitle from 'components/Molecules/CommonList/CommonTitle';
import LabelItem from 'components/Molecules/LabelList/LabelItem/';
import EmptyItem from './LabelItem/EmptyItem';

const StyledTitle = styled.span`
  color: ${({ theme }) => theme.colors.label};
  ${({ theme }) => theme.fontStyles.linkSmall};
`;

const LabelList = ({ labelData }: { labelData: LabelTypes[] }) => {
  const labels = labelData.map((label) => {
    return <LabelItem key={label.id} label={label} />;
  });

  return (
    <CommonList>
      <CommonTitle>
        <StyledTitle>{labelData.length}개의 타이틀</StyledTitle>
      </CommonTitle>
      {labelData.length ? labels : <EmptyItem />}
    </CommonList>
  );
};

export default LabelList;
